package com.example.Profile.adaptor.inbound.controller.apiModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthCredsApiModel {
    private String username;

    private String password;
}
