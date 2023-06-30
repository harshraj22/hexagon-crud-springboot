package com.example.Profile.domain.ports.outbound;

import com.example.Profile.adaptor.outbound.entity.UserProfileEntity;

public interface UserProfileOutbound {
    Boolean save(UserProfileEntity userProfileDTO);

    UserProfileEntity findUserProfileEntityByUsername(String username);
}
