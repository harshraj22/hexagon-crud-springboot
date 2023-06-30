package com.example.Profile.domain.service;

import com.example.Profile.domain.dto.UserProfileDTO;
import com.example.Profile.domain.ports.inbound.UserProfileInbound;
import com.example.Profile.domain.ports.outbound.UserProfileOutbound;
import com.example.Profile.helpers.mapper.UserProfileMapper;
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
        return userProfileMapper.map(userProfileOutbound.findUserProfileEntityByUsername(username));
    }
}
