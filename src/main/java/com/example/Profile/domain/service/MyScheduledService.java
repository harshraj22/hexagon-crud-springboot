package com.example.Profile.domain.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MyScheduledService {

    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask() {
        System.out.println("Running scheduled task at: " + LocalDateTime.now());
    }
}