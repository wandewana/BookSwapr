package com.bookrent.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class EmailService {

    public void sendReminderEmail(Long rentalId, LocalDateTime deadline) {
        // In a real application, this would integrate with an email client (e.g., SendGrid, AWS SES)
        log.info("--- SIMULATING SENDING EMAIL ---");
        log.info("To: user-associated-with-rental-{}", rentalId);
        log.info("Subject: Friendly Reminder: Your book rental is due soon!");
        log.info("Body: Hi there, this is a reminder that your book rental (ID: {}) is due on {}. Please return it on time to avoid any late fees.", rentalId, deadline);
        log.info("--- END OF SIMULATION ---");
    }
}
