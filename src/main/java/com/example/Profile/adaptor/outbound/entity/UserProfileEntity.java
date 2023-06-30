package com.example.Profile.adaptor.outbound.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
