package com.example.Profile.domain.ports.outbound;

import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;

import java.util.List;

public interface AuthCredsOutbound {
    AuthCredsEntity save(AuthCredsEntity authCredsEntity);

    AuthCredsEntity findByUsername(String username);

    List<AuthCredsEntity> findAll();
}
