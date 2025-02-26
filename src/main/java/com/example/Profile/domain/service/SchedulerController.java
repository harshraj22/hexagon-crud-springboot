package com.example.Profile.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/cron")
    public ResponseEntity<String> updateCronExpression(@RequestBody CronApiModel request) {
        try {
            String cronExpression = request.getCron();
            if (cronExpression == null || cronExpression.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Cron expression cannot be empty");
            }

            // Validate cron expression
            CronExpression.parse(cronExpression);

            schedulerService.updateCronExpression(cronExpression);
            return ResponseEntity.ok("Cron expression updated to: " + cronExpression);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid cron expression: " + e.getMessage());
        }
    }

    @GetMapping("/cron")
    public ResponseEntity<Map<String, String>> getCurrentCronExpression() {
        return ResponseEntity.ok(Map.of(
                "cronExpression", schedulerService.getCronExpression()
        ));
    }
}
