package com.example.Profile.adaptor.outbound.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "auth_creds")
public class AuthCredsEntity {

    @Column(nullable = false, unique = true, length = 40)
    private String username;

    @Column(nullable = false, length = 40)
    private String password;

}
