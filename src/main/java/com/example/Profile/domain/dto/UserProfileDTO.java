package com.example.Profile.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileDTO {

    private String username;

    private String name;

    private String email;

    private Integer age;
}
