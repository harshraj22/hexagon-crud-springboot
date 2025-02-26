package com.example.Profile.domain.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CronApiModel {
    private String cronExpression;
}
