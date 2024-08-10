package com.turkcell.sol.catalog_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
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

}
