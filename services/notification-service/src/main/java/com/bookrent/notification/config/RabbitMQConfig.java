package com.bookrent.notification.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_RENTAL_CREATED = "rental_created";

    @Bean
    public Queue rentalCreatedQueue() {
        return new Queue(QUEUE_RENTAL_CREATED);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        // The ObjectMapper provided by Spring Boot is already configured with the JavaTimeModule.
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
