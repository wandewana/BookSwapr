package com.bookrent.rental.service;

import com.bookrent.rental.dto.RentalRequest;
import com.bookrent.rental.model.Rental;
import com.bookrent.rental.model.RentalStatus;
import com.bookrent.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public Rental rentBook(RentalRequest rentalRequest) {
        rentalRepository.findByBookIdAndStatus(rentalRequest.getBookId(), RentalStatus.RENTED)
                .ifPresent(rental -> {
                    throw new IllegalStateException("Book is already rented.");
                });

        Rental rental = Rental.builder()
                .userId(rentalRequest.getUserId())
                .bookId(rentalRequest.getBookId())
                .rentedAt(LocalDateTime.now())
                .deadline(LocalDateTime.now().plusWeeks(2))
                .status(RentalStatus.RENTED)
                .build();

        return rentalRepository.save(rental);
    }

    public Rental returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found."));

        rental.setStatus(RentalStatus.RETURNED);
        rental.setReturnedAt(LocalDateTime.now());

        return rentalRepository.save(rental);
    }
}
