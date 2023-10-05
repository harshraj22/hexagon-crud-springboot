package com.example.Profile.helpers.mapper;

import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;
import com.example.Profile.domain.dto.AuthCredsDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthCredsMapper {

    public AuthCredsEntity map(AuthCredsDTO authCredsDTO) {
        AuthCredsEntity authCredsEntity = new AuthCredsEntity();
        authCredsEntity.setUsername(authCredsDTO.getUsername());
        authCredsEntity.setPassword(authCredsDTO.getPassword());
        return authCredsEntity;
    }

    public AuthCredsDTO map(AuthCredsEntity authCredsEntity) {
        AuthCredsDTO authCredsDTO = new AuthCredsDTO();
        authCredsDTO.setUsername(authCredsEntity.getUsername());
        authCredsDTO.setPassword(authCredsEntity.getPassword());
        return authCredsDTO;
    }


}
