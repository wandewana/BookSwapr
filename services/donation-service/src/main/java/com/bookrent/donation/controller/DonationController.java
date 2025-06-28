package com.bookrent.donation.controller;

import com.bookrent.donation.dto.DonationRequest;
import com.bookrent.donation.model.Donation;
import com.bookrent.donation.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<Donation> createDonation(@RequestBody DonationRequest donationRequest) {
        return ResponseEntity.ok(donationService.createDonation(donationRequest));
    }
}
