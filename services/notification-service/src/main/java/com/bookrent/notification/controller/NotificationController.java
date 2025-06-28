package com.bookrent.notification.controller;

import com.bookrent.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/deadlines")
    public ResponseEntity<Map<Long, LocalDateTime>> getAllDeadlines() {
        return ResponseEntity.ok(notificationService.getAllDeadlines());
    }
}
