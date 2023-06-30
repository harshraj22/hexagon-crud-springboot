package com.example.Profile.adaptor.outbound.repository;

import com.example.Profile.adaptor.outbound.entity.UserProfileEntity;
import com.example.Profile.domain.ports.outbound.UserProfileOutbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String>, UserProfileOutbound {
}
