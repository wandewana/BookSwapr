package com.bookrent.notification.scheduler;

import com.bookrent.notification.service.EmailService;
import com.bookrent.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReminderScheduler {

    private final NotificationService notificationService;
    private final EmailService emailService;

    @Scheduled(cron = "0 * * * * ?") // Runs every minute
    public void checkDeadlinesAndSendReminders() {
        log.info("Running scheduled job to check for upcoming deadlines...");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderThreshold = now.plusDays(1);

        notificationService.getAllDeadlines().forEach((rentalId, deadline) -> {
            if (deadline.isAfter(now) && deadline.isBefore(reminderThreshold)) {
                log.info("Found upcoming deadline for rental ID {}. Triggering reminder.", rentalId);
                emailService.sendReminderEmail(rentalId, deadline);
            }
        });
    }
}
