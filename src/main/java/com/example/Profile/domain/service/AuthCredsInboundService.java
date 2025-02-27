package com.example.Profile.domain.service;

import com.example.Profile.domain.dto.AuthCredsDTO;
import com.example.Profile.domain.mappers.AuthCredsMapper;
import com.example.Profile.domain.ports.inbound.AuthCredsInbound;
import com.example.Profile.domain.ports.outbound.AuthCredsOutbound;
import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;
import com.example.Profile.infrastructure.kafka.avro.AuthRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AuthCredsInboundService implements AuthCredsInbound {
    @Autowired
    private KafkaTemplate<String, AuthRecord> kafkaTemplate;

    @Autowired
    AuthCredsOutbound authCredsOutbound;

    @Autowired
    AuthCredsMapper authCredsMapper;

    @Value("${kafka.topic}")
    private String topic;

    @Override
    public Boolean postAuthCreds(AuthCredsDTO authCredsDTO) {
        authCredsOutbound.save(authCredsMapper.map(authCredsDTO));
        // publish event on kafka here using AuthRecord.java
        AuthRecord authRecord = AuthRecord.newBuilder()
                .setUsername(authCredsDTO.getUsername())
                .setPassword(authCredsDTO.getPassword())
                .setEmail("")
                .build();

        CompletableFuture<SendResult<String, AuthRecord>> result = kafkaTemplate.send(topic, authCredsDTO.getUsername(), authRecord);

        result.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                System.err.println("Error sending message: " + exception.getMessage());
            } else {
                System.out.println("Message sent successfully to partition " +
                        sendResult.getRecordMetadata().partition() +
                        " with offset " + sendResult.getRecordMetadata().offset());
            }
        });

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
