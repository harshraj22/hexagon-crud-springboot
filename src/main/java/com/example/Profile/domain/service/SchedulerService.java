package com.example.Profile.domain.service;

import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerService {
    private String cronExpression = "0 0 * * * *"; // default hourly
    private final List<ScheduledTaskRegistrar> taskRegistrars = new ArrayList<>();

    public void updateCronExpression(String newCron) {
        this.cronExpression = newCron;
        // Just destroy and recreate the TaskRegistrar
        taskRegistrars.forEach(taskRegistrar -> {
            taskRegistrar.destroy();
            taskRegistrar.afterPropertiesSet();
        });
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void addTaskRegistrar(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrars.add(taskRegistrar);
    }
}