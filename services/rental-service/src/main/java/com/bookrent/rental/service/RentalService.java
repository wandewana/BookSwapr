package com.bookrent.rental.service;

import com.bookrent.rental.dto.RentalRequest;
import com.bookrent.rental.model.Rental;
import com.bookrent.rental.model.RentalStatus;
import com.bookrent.rental.repository.RentalRepository;
import com.bookrent.rental.config.RabbitMQConfig;
import com.bookrent.rental.dto.RentalEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RabbitTemplate rabbitTemplate;

    public Rental rentBook(RentalRequest rentalRequest) {
        rentalRepository.findByBookIdAndStatus(rentalRequest.getBookId(), RentalStatus.RENTED)
                .ifPresent(rental -> {
                    throw new IllegalStateException("Book is already rented.");
                });

        Rental rental = Rental.builder()
                .userId(rentalRequest.getUserId())
                .bookId(rentalRequest.getBookId())
                .rentedAt(LocalDateTime.now())
                .deadline(LocalDateTime.now().plusDays(7))
                .status(RentalStatus.RENTED)
                .build();

        Rental savedRental = rentalRepository.save(rental);

        RentalEvent event = new RentalEvent(savedRental.getId(), savedRental.getBookId(), savedRental.getUserId(), savedRental.getStatus(), LocalDateTime.now(), savedRental.getDeadline());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_RENTAL_CREATED, event);

        return savedRental;
    }

    public Rental returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found."));

        rental.setStatus(RentalStatus.RETURNED);
        rental.setReturnedAt(LocalDateTime.now());

        Rental savedRental = rentalRepository.save(rental);

        RentalEvent event = new RentalEvent(savedRental.getId(), savedRental.getBookId(), savedRental.getUserId(), savedRental.getStatus(), LocalDateTime.now(), savedRental.getDeadline());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_RENTAL_RETURNED, event);

        return savedRental;
    }
}
