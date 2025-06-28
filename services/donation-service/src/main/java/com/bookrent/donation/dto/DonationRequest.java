package com.bookrent.donation.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DonationRequest {
    private Long rentalId;
    private BigDecimal donationAmount;
}
