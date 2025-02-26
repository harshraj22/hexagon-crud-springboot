package com.example.Profile.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;


@Configuration
@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    @Autowired
    private SchedulerService schedulerService;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
        schedulerService.addTaskRegistrar(taskRegistrar);
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("Running scheduled task");
                },
                triggerContext -> {
                    return new CronTrigger(schedulerService.getCronExpression())
                            .nextExecutionTime(triggerContext).toInstant();
                }
        );
    }
}