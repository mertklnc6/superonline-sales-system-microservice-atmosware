package com.turkcell.sol.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;
import com.turkcell.sol.*;
import com.turkcell.sol.order_service.client.PaymentServiceClient;
import com.turkcell.sol.order_service.controller.client.CatalogClient;
import com.turkcell.sol.order_service.controller.client.StockClient;
import com.turkcell.sol.order_service.dto.OrderItemDto;
import com.turkcell.sol.order_service.dto.requests.CreateOrderRequest;
import com.turkcell.sol.order_service.dto.requests.StockRequest;
import com.turkcell.sol.order_service.dto.requests.UpdateOrderRequest;
import com.turkcell.sol.order_service.dto.responses.*;
import com.turkcell.sol.order_service.mapper.OrderItemMapper;
import com.turkcell.sol.order_service.mapper.OrderMapper;
import com.turkcell.sol.order_service.model.Enums.OrderStatus;
import com.turkcell.sol.order_service.model.Order;
import com.turkcell.sol.order_service.model.OrderItem;
import com.turkcell.sol.order_service.rabbitMQ.sender.OrderSender;
import com.turkcell.sol.order_service.repository.OrderRepository;
import com.turkcell.sol.order_service.service.OrderItemService;
import com.turkcell.sol.order_service.service.OrderService;
import com.turkcell.sol.order_service.service.rules.OrderBusinessRules;
import com.turkcell.sol.order_service.shared.dto.rabbitMQ.Order.OrderNotificationEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final CatalogClient catalogClient;
    private final StockClient stockClient;
    private final OrderItemMapper orderItemMapper;
    private final PaymentServiceClient paymentServiceClient;
    private final OrderBusinessRules orderBusinessRules;
    private final OrderSender orderSender;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    @Override
    public CreatedOrderResponse create(CreateOrderRequest createOrderRequest) {

        List<String> productIds = createOrderRequest.items().stream()
                .map(OrderItemDto::productId)
                .toList();

        List<GetProductResponse> products = catalogClient.getAllByIds(productIds);

        Order order = orderMapper.toOrder(createOrderRequest);
        order.setTotalPrice(products.stream().mapToDouble(GetProductResponse::price).sum());
        order.setStatus(OrderStatus.IN_PROGRESS);

        orderRepository.save(order);

        orderSender.send(new OrderNotificationEvent(order.getId().toString(), "Order created."));

        List<OrderItem> orderItemList = createOrderRequest.items().stream()
                .map(item -> {

                    GetProductResponse product = products.stream()
                            .filter(p -> p.productId().toString().equals(item.productId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Product not found: " + item.productId()));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(product.productId().toString());
                    orderItem.setPrice(product.price());
                    orderItem.setQuantity(item.quantity());
                    orderItem.setProductName(product.name());
                    orderItem.setOrder(order);

                    return orderItem;
                })
                .collect(Collectors.toList());

        orderItemService.add(orderItemList);

        this.decreaseStockForOrderItems(orderItemList);

        order.setStatus(OrderStatus.PAYMENT_PENDING);
        orderRepository.save(order);

        orderSender.send(new OrderNotificationEvent(order.getId().toString(),"Payment Pending."));

        PaymentRequest paymentRequest = PaymentRequest.newBuilder()
                .setOrderId(order.getId().toString())
                .setSuccess(createOrderRequest.paymentRequest().success())
                .setAmount(order.getTotalPrice())
                .setPaymentMethod(createOrderRequest.paymentRequest().paymentMethod())
                .setPaymentDetails(constructPaymentDetails(
                        createOrderRequest.paymentRequest().paymentMethod(),
                        objectMapper.convertValue(createOrderRequest.paymentRequest().paymentDetails(), JsonNode.class)))
                .build();


        PaymentResponse paymentResponse = paymentServiceClient.processPayment(paymentRequest);

        if (paymentResponse.getSuccess()) {

            order.setStatus(OrderStatus.COMPLETED);

            stockClient.removeOutOfStockProductsFromCatalog();

            orderRepository.save(order);

            orderSender.send(new OrderNotificationEvent(order.getId().toString(),"Order Completed."));
        } else {

            this.rollBackStocks(orderItemList);

            order.setStatus(OrderStatus.FAILED);

            orderRepository.save(order);

            orderSender.send(new OrderNotificationEvent(order.getId().toString(),"Order Failed."));
        }

        return orderMapper.toCreatedOrderResponse(order);
    }

    private PaymentDetails constructPaymentDetails(String paymentMethod, JsonNode paymentDetailsNode) {
        try {
            PaymentDetails.Builder paymentDetailsBuilder = PaymentDetails.newBuilder();

            if ("CREDIT_CARD".equals(paymentMethod)) {
                CreditCardPaymentDetails.Builder creditCardBuilder = CreditCardPaymentDetails.newBuilder();
                JsonFormat.parser().merge(paymentDetailsNode.toString(), creditCardBuilder);
                paymentDetailsBuilder.setCreditCardDetails(creditCardBuilder.build());
            } else if ("BANK_TRANSFER".equals(paymentMethod)) {
                BankTransferPaymentDetails.Builder bankTransferBuilder = BankTransferPaymentDetails.newBuilder();
                JsonFormat.parser().merge(paymentDetailsNode.toString(), bankTransferBuilder);
                paymentDetailsBuilder.setBankTransferDetails(bankTransferBuilder.build());
            } else {
                throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
            }

            return paymentDetailsBuilder.build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting payment details to " + paymentMethod, e);
        }
    }




    private void decreaseStockForOrderItems(List<OrderItem> orderItemList) {
        List<StockRequest> stockRequestList = new ArrayList<>();
        for (OrderItem item : orderItemList) {
            StockRequest stockRequest = new StockRequest(item.getProductId(), item.getQuantity());
            stockRequestList.add(stockRequest);
        }
        stockClient.decreaseStock(stockRequestList);
    }

    private void rollBackStocks(List<OrderItem> orderItemList) {
        List<StockRequest> stockRequestList = new ArrayList<>();
        for (OrderItem item : orderItemList) {
            StockRequest stockRequest = new StockRequest(item.getProductId(), item.getQuantity());
            stockRequestList.add(stockRequest);
        }
        stockClient.rollbackStock(stockRequestList);
    }

    private void handleFailedOrder(Order order) {
        order.setStatus(OrderStatus.FAILED);
        orderRepository.save(order);
    }


    @Override
    public GetOrderResponse getById(UUID id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        orderBusinessRules.productShouldBeExist(orderOptional);

        return orderMapper.toGetOrderResponse(orderOptional.get());
    }

    @Override
    public List<GetOrderResponse> getAll() {

        List<Order> orderList = orderRepository.findAll();

        return orderMapper.toGetOrderResponse(orderList);
    }

    @Override
    public DeletedOrderResponse delete(UUID id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        orderBusinessRules.productShouldBeExist(orderOptional);

        Order order = orderOptional.get();
        order.setDeletedDate(LocalDateTime.now());

        orderRepository.save(order);

        return orderMapper.toDeletedOrderResponse(order);
    }
}
