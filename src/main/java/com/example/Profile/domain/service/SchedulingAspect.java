package com.example.Profile.domain.service;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Aspect
@Component
public class SchedulingAspect {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private TaskScheduler taskScheduler;

    private final Map<Method, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        schedulerService.setScheduledTasksMap(scheduledTasks);
    }

    @Around("@annotation(scheduled)")
    public Object around(ProceedingJoinPoint joinPoint, Scheduled scheduled) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // Cancel existing schedule if any
        ScheduledFuture<?> scheduledFuture = scheduledTasks.get(method);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }

        // Create new schedule
        scheduledFuture = taskScheduler.schedule(
                () -> {
                    try {
                        joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                },
                triggerContext -> new CronTrigger(schedulerService.getCronExpression())
                        .nextExecutionTime(triggerContext).toInstant()
        );

        scheduledTasks.put(method, scheduledFuture);
        return null;
    }
}