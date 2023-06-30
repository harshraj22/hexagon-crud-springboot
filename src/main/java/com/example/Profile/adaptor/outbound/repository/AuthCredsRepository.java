package com.example.Profile.adaptor.outbound.repository;

import com.example.Profile.adaptor.outbound.entity.AuthCredsEntity;
import com.example.Profile.domain.ports.outbound.AuthCredsOutbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCredsRepository extends JpaRepository<AuthCredsEntity, String>, AuthCredsOutbound {
    AuthCredsEntity findAuthCredsEntityByUsername(String username);
}
