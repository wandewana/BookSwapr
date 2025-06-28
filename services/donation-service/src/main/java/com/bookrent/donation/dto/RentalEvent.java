package com.bookrent.donation.dto;

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
    private String status;
    private LocalDateTime timestamp;
    private LocalDateTime deadline;
}
