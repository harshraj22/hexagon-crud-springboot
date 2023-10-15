package com.example.Profile.domain.ports.outbound.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "user_profile")
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", unique = true)
    @NotNull

    private AuthCredsEntity authCreds;

    @Column(nullable = false,length = 40)
    private String name;
    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false)
    private Integer age;
}
