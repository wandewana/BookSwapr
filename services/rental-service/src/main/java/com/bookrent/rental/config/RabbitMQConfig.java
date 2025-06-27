package com.bookrent.rental.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "rental_exchange";
    public static final String QUEUE_RENTAL_CREATED = "rental_created";
    public static final String QUEUE_RENTAL_RETURNED = "rental_returned";
    public static final String ROUTING_KEY_RENTAL_CREATED = "rental.created";
    public static final String ROUTING_KEY_RENTAL_RETURNED = "rental.returned";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue rentalCreatedQueue() {
        return new Queue(QUEUE_RENTAL_CREATED);
    }

    @Bean
    public Queue rentalReturnedQueue() {
        return new Queue(QUEUE_RENTAL_RETURNED);
    }

    @Bean
    public Binding bindingRentalCreated(Queue rentalCreatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(rentalCreatedQueue).to(exchange).with(ROUTING_KEY_RENTAL_CREATED);
    }

    @Bean
    public Binding bindingRentalReturned(Queue rentalReturnedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(rentalReturnedQueue).to(exchange).with(ROUTING_KEY_RENTAL_RETURNED);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        // The ObjectMapper provided by Spring Boot is already configured with the JavaTimeModule.
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
