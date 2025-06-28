package com.bookrent.notification.listener;

import com.bookrent.notification.config.RabbitMQConfig;
import com.bookrent.notification.dto.RentalEvent;
import com.bookrent.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RentalEventListener {

    private final NotificationService notificationService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RENTAL_CREATED)
    public void handleRentalCreated(RentalEvent rentalEvent) {
        log.info("Received rental created event: {}", rentalEvent);
        if (rentalEvent.getDeadline() != null) {
            notificationService.saveRentalDeadline(rentalEvent.getRentalId(), rentalEvent.getDeadline());
            log.info("Saved deadline for rentalId: {}. Deadline: {}", rentalEvent.getRentalId(), rentalEvent.getDeadline());
        }
    }
}
