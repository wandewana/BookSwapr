package com.bookrent.rental.repository;

import com.bookrent.rental.model.Rental;
import com.bookrent.rental.model.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findByBookIdAndStatus(Long bookId, RentalStatus status);
}
