package com.example.Profile.adaptor.outbound.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfileEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private AuthCredsEntity authCreds;

    @Column(nullable = false,length = 40)
    private String name;
    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false)
    private Integer age;
}
