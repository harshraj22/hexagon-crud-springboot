package com.example.Profile.adaptor.outbound.repository;

import com.example.Profile.domain.ports.outbound.UserProfileOutbound;
import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;
import com.example.Profile.domain.ports.outbound.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String>, UserProfileOutbound {
    UserProfileEntity findByAuthCreds(AuthCredsEntity authCreds);

//    @Query(value = "INSERT INTO user_profile (username, name, email, age) " +
//            "VALUES (:#{#userProfileEntity.authCreds.username}, " +
//            ":#{#userProfileEntity.name}, :#{#userProfileEntity.email}, :#{#userProfileEntity.age}) " +
//            "RETURNING *", nativeQuery = true)
    UserProfileEntity save(UserProfileEntity userProfileEntity);

}
