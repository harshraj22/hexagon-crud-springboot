package com.example.Profile.adaptor.outbound.repository;

import com.example.Profile.adaptor.outbound.entity.AuthCredsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCredsRepository extends JpaRepository<AuthCredsEntity, String> {
    AuthCredsEntity findAuthCredsEntityByUsername(String username);
}
