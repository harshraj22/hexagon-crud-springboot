package com.example.Profile.domain.service;

import com.example.Profile.domain.dto.AuthCredsDTO;
import com.example.Profile.domain.mappers.AuthCredsMapper;
import com.example.Profile.domain.ports.inbound.AuthCredsInbound;
import com.example.Profile.domain.ports.outbound.AuthCredsOutbound;
import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthCredsInboundService implements AuthCredsInbound {

    @Autowired
    AuthCredsOutbound authCredsOutbound;

    @Autowired
    AuthCredsMapper authCredsMapper;

    // Write a scheduled task that gets all AuthCredsDTO from the database every 30 seconds
    // and prints them to the console
//    @Scheduled(fixedRate = 30000)
//    public void printAllAuthCreds() {
//        List<AuthCredsDTO> authCredsDTOList = getAllAuthCreds();
//        System.out.println("Printing all AuthCredsDTO from the database as scheduled activity:");
//        authCredsDTOList.forEach(System.out::println);
//    }

    @Override
    public Boolean postAuthCreds(AuthCredsDTO authCredsDTO) {
        authCredsOutbound.save(authCredsMapper.map(authCredsDTO));
        return true;
    }

    @Override
    public AuthCredsDTO getAuthCreds(String username) {
        return authCredsMapper.map(authCredsOutbound.findByUsername(username));
    }

    @Override
    public List<AuthCredsDTO> getAllAuthCreds() {
        List<AuthCredsEntity> authCredsEntities = authCredsOutbound.findAll(); // Implement this method in AuthCredsOutbound
        return authCredsEntities.stream()
                .map(authCredsMapper::map) // Map AuthCredsEntity to AuthCredsDTO
                .collect(Collectors.toList());
    }
}
