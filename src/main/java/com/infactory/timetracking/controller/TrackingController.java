package com.infactory.timetracking.controller;

import com.infactory.timetracking.service.AlreadyCheckInException;
import com.infactory.timetracking.service.NoCheckInFoundException;
import com.infactory.timetracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TrackingController {

    @Autowired
    TrackingService trackingService;

    @GetMapping("/checkIn")
    public ResponseEntity<?> checkIn() {
        try {
            trackingService.checkIn();
            return ResponseEntity.ok("User has checked in @ " + LocalDateTime.now());
        } catch (AlreadyCheckInException e) {
            return ResponseEntity.ok("User has already checked in");
        }
    }

    @GetMapping("/checkOut")
    public ResponseEntity<?> checkOut() {
        try {
            trackingService.checkOut();
            return ResponseEntity.ok("User has checked out @ " + LocalDateTime.now());
        } catch (NoCheckInFoundException e) {
            return ResponseEntity.ok("User is not checked in!");
        }
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        return ResponseEntity.ok(trackingService.getSummary());
    }
}
