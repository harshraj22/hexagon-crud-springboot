package com.example.Profile.domain.service;

import com.example.Profile.domain.dto.AuthCredsDTO;
import com.example.Profile.domain.ports.inbound.AuthCredsInbound;
import com.example.Profile.domain.ports.outbound.AuthCredsOutbound;
import com.example.Profile.domain.mappers.AuthCredsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthCredsInboundService implements AuthCredsInbound {

    @Autowired
    AuthCredsOutbound authCredsOutbound;

    @Autowired
    AuthCredsMapper authCredsMapper;

    @Override
    public Boolean postAuthCreds(AuthCredsDTO authCredsDTO) {
        authCredsOutbound.save(authCredsMapper.map(authCredsDTO));
        return true;
    }

    @Override
    public AuthCredsDTO getAuthCreds(String username) {
        return authCredsMapper.map(authCredsOutbound.findAuthCredsEntityByUsername(username));
    }
}
