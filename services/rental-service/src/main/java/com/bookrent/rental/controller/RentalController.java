package com.bookrent.rental.controller;

import com.bookrent.rental.dto.RentalRequest;
import com.bookrent.rental.model.Rental;
import com.bookrent.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<Rental> rentBook(@RequestBody RentalRequest rentalRequest) {
        return ResponseEntity.ok(rentalService.rentBook(rentalRequest));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Rental> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.returnBook(id));
    }
}
