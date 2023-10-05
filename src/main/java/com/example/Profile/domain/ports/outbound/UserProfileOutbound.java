package com.example.Profile.domain.ports.outbound;

import com.example.Profile.domain.ports.outbound.entity.UserProfileEntity;

public interface UserProfileOutbound {
    UserProfileEntity save(UserProfileEntity userProfileEntity);

    UserProfileEntity findUserProfileEntityByUsername(String username);
}
