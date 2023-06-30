package com.example.Profile.domain.ports.inbound;

import com.example.Profile.domain.dto.UserProfileDTO;

public interface UserProfileInbound {
    Boolean postUserProfile (UserProfileDTO userProfileDTO);

    UserProfileDTO getUserProfile (String username);
}
