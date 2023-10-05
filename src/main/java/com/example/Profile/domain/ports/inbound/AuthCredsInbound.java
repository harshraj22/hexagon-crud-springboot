package com.example.Profile.domain.ports.inbound;

import com.example.Profile.domain.dto.AuthCredsDTO;

import java.util.List;

public interface AuthCredsInbound {
    Boolean postAuthCreds(AuthCredsDTO authCredsDTO);

    AuthCredsDTO getAuthCreds(String username);

    List<AuthCredsDTO> getAllAuthCreds();
}
