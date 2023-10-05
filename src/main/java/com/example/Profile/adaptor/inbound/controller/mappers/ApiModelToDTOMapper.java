package com.example.Profile.adaptor.inbound.controller.mappers;

import com.example.Profile.adaptor.inbound.controller.apiModels.AuthCredsApiModel;
import com.example.Profile.adaptor.inbound.controller.apiModels.UserProfileApiModel;
import com.example.Profile.domain.dto.AuthCredsDTO;
import com.example.Profile.domain.dto.UserProfileDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ApiModelToDTOMapper {
    AuthCredsDTO toAuthCredsDTO(AuthCredsApiModel authCredsApiModel);

    UserProfileDTO toUserProfileDTO(UserProfileApiModel userProfileApiModel);
}
