package com.example.Profile.domain.ports.outbound;

import com.example.Profile.adaptor.outbound.entity.AuthCredsEntity;

public interface AuthCredsOutbound {
    Boolean save(AuthCredsEntity authCredsDTO);

    AuthCredsEntity findAuthCredsEntityByUsername(String username);
}
