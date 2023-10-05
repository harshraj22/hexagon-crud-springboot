package com.example.Profile.domain.ports.outbound;

import com.example.Profile.domain.ports.outbound.entity.AuthCredsEntity;
import com.example.Profile.domain.ports.outbound.entity.UserProfileEntity;

public interface UserProfileOutbound {
    UserProfileEntity save(UserProfileEntity userProfileEntity);

    UserProfileEntity findByAuthCreds(AuthCredsEntity authCreds);

}
