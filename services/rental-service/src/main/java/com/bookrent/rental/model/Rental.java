package com.bookrent.rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long bookId;

    private LocalDateTime rentedAt;

    private LocalDateTime deadline;

    private LocalDateTime returnedAt;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;
}
