package com.example.Profile.domain.service;

import com.example.Profile.domain.dto.UserProfileDTO;
import com.example.Profile.domain.ports.inbound.UserProfileInbound;
import com.example.Profile.domain.ports.outbound.UserProfileOutbound;
import com.example.Profile.domain.mappers.UserProfileMapper;
import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileInboundService implements UserProfileInbound {

    @Autowired
    UserProfileOutbound userProfileOutbound;

    @Autowired
    UserProfileMapper userProfileMapper;

    @Override
    public Boolean postUserProfile(UserProfileDTO userProfileDTO) {
        userProfileOutbound.save(userProfileMapper.map(userProfileDTO));
        return true;
    }

    @Override
    public UserProfileDTO getUserProfile(String username) {
        AuthCredsEntity authCredsEntity = AuthCredsEntity.builder().username(username).build();
        return userProfileMapper.map(userProfileOutbound.findByAuthCreds(authCredsEntity));
    }
}
