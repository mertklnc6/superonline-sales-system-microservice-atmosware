package com.tukcell.sol.notification_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_notifications")
public class OrderNotification {
    @Id
    @Field("_id")
    private String id;
    private String orderId;
    private String message;
    private LocalDateTime createdDate;
}
