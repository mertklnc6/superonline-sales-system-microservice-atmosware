package com.turkcell.sol.order_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue productCreatedQueue()
    {
        return new Queue("product-created", false);
    }

    @Bean
    public Queue productUpdatedQueue(){
        return new Queue("product-updated", false);
    }

    @Bean
    public Queue productDeletedQueue(){
        return new Queue("product-deleted", false);
    }

    @Bean
    public Queue outOfStockQueue(){
        return new Queue("out-of-stock", false);
    }

    @Bean
    public Queue orderNotificationQueue(){
        return new Queue("order-notification", false);
    }

}
