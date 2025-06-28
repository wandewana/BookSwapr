package com.bookrent.donation.listener;

import com.bookrent.donation.config.RabbitMQConfig;
import com.bookrent.donation.dto.RentalEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RentalReturnedListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RENTAL_RETURNED)
    public void handleRentalReturned(RentalEvent rentalEvent) {
        log.info("Received rental returned event: {}", rentalEvent);
    }
}
