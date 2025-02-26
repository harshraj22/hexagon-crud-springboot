package com.example.Profile.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/update")
    public ResponseEntity<String> updateSchedule(@RequestBody CronApiModel cronApiModel) {
        try {
            String cronExpression = cronApiModel.getCronExpression();
            // Remove any quotes and trim whitespace
            String sanitizedCron = cronExpression.replaceAll("[\"']", "").trim();

            // Validate cron expression
            CronExpression.parse(sanitizedCron);

            schedulerService.updateCronExpression(sanitizedCron);
            return ResponseEntity.ok("Schedule updated to: " + sanitizedCron);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Invalid cron expression: " + e.getMessage());
        }
    }
}