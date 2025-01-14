package com.example.Profile.adaptor.outbound.repository;

import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;
import com.example.Profile.domain.ports.outbound.AuthCredsOutbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthCredsRepository extends JpaRepository<AuthCredsEntity, String>, AuthCredsOutbound {
    AuthCredsEntity findByUsername(String username);

    AuthCredsEntity save(AuthCredsEntity authCredsEntity);

    List<AuthCredsEntity> findAll();

    void deleteAll();
}
