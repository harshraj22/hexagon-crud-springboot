package com.example.Profile.adaptor.inbound.controller.apiModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileApiModel {
    private String username;

    private String name;

    private String email;

    private Integer age;
}
