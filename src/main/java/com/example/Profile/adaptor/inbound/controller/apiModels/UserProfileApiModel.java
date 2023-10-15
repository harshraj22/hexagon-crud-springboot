package com.example.Profile.adaptor.inbound.controller.apiModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileApiModel {
    private String username;

    private String name;

    private String email;

    private Integer age;
}
