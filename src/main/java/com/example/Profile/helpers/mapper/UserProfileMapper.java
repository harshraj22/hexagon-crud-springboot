package com.example.Profile.helpers.mapper;

import com.example.Profile.adaptor.outbound.entity.AuthCredsEntity;
import com.example.Profile.adaptor.outbound.entity.UserProfileEntity;
import com.example.Profile.domain.dto.UserProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public UserProfileEntity map(UserProfileDTO userProfileDTO) {
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setName(userProfileDTO.getName());
        userProfileEntity.setEmail(userProfileDTO.getEmail());
        userProfileEntity.setAge(userProfileDTO.getAge());
        AuthCredsEntity authCredsEntity = new AuthCredsEntity();
        authCredsEntity.setUsername(userProfileDTO.getUsername());
        userProfileEntity.setAuthCreds(authCredsEntity);
        return userProfileEntity;
    }

    public UserProfileDTO map(UserProfileEntity userProfileEntity) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(userProfileEntity.getAuthCreds().getUsername());
        userProfileDTO.setName(userProfileEntity.getName());
        userProfileDTO.setEmail(userProfileEntity.getEmail());
        userProfileDTO.setAge(userProfileEntity.getAge());
        return userProfileDTO;
    }
}
