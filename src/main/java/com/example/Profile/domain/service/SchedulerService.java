package com.example.Profile.domain.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerService {
    private String cronExpression = "0 0 * * * *"; // default hourly
    private Map<Method, ScheduledFuture<?>> scheduledTasks;

    public void updateCronExpression(String newCron) {
        this.cronExpression = newCron;
        // Cancel and reschedule all tasks
        if (scheduledTasks != null) {
            scheduledTasks.values().forEach(future -> future.cancel(false));
        }
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setScheduledTasksMap(Map<Method, ScheduledFuture<?>> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }
}