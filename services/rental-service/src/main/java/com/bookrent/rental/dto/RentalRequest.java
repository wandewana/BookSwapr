package com.bookrent.rental.dto;

import lombok.Data;

@Data
public class RentalRequest {
    private Long userId;
    private Long bookId;
}
