package com.bookrent.notification.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {

    private final Map<Long, LocalDateTime> rentalDeadlines = new ConcurrentHashMap<>();

    public void saveRentalDeadline(Long rentalId, LocalDateTime deadline) {
        rentalDeadlines.put(rentalId, deadline);
    }

    public LocalDateTime getRentalDeadline(Long rentalId) {
        return rentalDeadlines.get(rentalId);
    }

    public Map<Long, LocalDateTime> getAllDeadlines() {
        return rentalDeadlines;
    }
}
