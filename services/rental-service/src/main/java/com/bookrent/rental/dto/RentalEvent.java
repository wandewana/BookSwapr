package com.bookrent.rental.dto;

import com.bookrent.rental.model.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalEvent {
    private Long rentalId;
    private Long bookId;
    private Long userId;
    private RentalStatus status;
    private LocalDateTime timestamp;
}
