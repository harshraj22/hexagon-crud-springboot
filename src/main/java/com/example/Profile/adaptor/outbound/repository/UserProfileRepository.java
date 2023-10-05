package com.example.Profile.adaptor.outbound.repository;

import com.example.Profile.domain.ports.outbound.entity.UserProfileEntity;
import com.example.Profile.domain.ports.outbound.UserProfileOutbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String>, UserProfileOutbound {
    @Query(value = "SELECT * FROM user_profile WHERE username = :username", nativeQuery = true)
    UserProfileEntity findUserProfileEntityByUsername(@Param("username") String username);

    @Query(value = "INSERT INTO user_profile (username, name, email, age) " +
            "VALUES (:#{#userProfileEntity.authCreds.username}, " +
            ":#{#userProfileEntity.name}, :#{#userProfileEntity.email}, :#{#userProfileEntity.age}) " +
            "RETURNING *", nativeQuery = true)
    UserProfileEntity save(@Param("userProfileEntity") UserProfileEntity userProfileEntity);

}
