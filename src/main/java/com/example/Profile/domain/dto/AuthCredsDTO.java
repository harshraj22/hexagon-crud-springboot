package com.example.Profile.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthCredsDTO {

    private String username;

    private String password;
}
