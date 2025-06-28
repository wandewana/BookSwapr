package com.bookrent.donation.service;

import com.bookrent.donation.dto.DonationRequest;
import com.bookrent.donation.model.Donation;
import com.bookrent.donation.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private static final BigDecimal COMMISSION_RATE = new BigDecimal("0.10");

    public Donation createDonation(DonationRequest donationRequest) {
        BigDecimal commissionAmount = donationRequest.getDonationAmount().multiply(COMMISSION_RATE);
        Donation donation = Donation.builder()
                .rentalId(donationRequest.getRentalId())
                .donationAmount(donationRequest.getDonationAmount())
                .commissionAmount(commissionAmount)
                .timestamp(LocalDateTime.now())
                .build();
        return donationRepository.save(donation);
    }
}
