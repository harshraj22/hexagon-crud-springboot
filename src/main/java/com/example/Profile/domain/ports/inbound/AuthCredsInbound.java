package com.example.Profile.domain.ports.inbound;

import com.example.Profile.domain.dto.AuthCredsDTO;

public interface AuthCredsInbound {
    Boolean postAuthCreds(AuthCredsDTO authCredsDTO);

    AuthCredsDTO getAuthCreds(String username);
}
