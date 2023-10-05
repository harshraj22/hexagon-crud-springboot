package com.example.Profile.adaptor.inbound.controller;


import com.example.Profile.adaptor.inbound.controller.apiModels.AuthCredsApiModel;
import com.example.Profile.adaptor.inbound.controller.apiModels.UserProfileApiModel;
import com.example.Profile.adaptor.inbound.controller.mappers.ApiModelToDTOMapper;
import com.example.Profile.domain.dto.AuthCredsDTO;
import com.example.Profile.domain.dto.UserProfileDTO;
import com.example.Profile.domain.ports.inbound.AuthCredsInbound;
import com.example.Profile.domain.ports.inbound.UserProfileInbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    AuthCredsInbound authCredsInbound;

    @Autowired
    UserProfileInbound userProfileInbound;

    @Autowired
    ApiModelToDTOMapper apiModelToDTOMapper;

    // GET method for retrieving AuthCredsDTO
    @GetMapping("/authcreds/{username}")
    public ResponseEntity<AuthCredsDTO> getAuthCreds(@PathVariable String username) {
        AuthCredsDTO authCreds = authCredsInbound.getAuthCreds(username);
        if (authCreds != null) {
            return ResponseEntity.ok(authCreds);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST method for creating AuthCredsDTO
    @PostMapping("/authcreds")
    public ResponseEntity<Boolean> createAuthCreds(@RequestBody AuthCredsApiModel authCredsApiModel) {
        Boolean created = authCredsInbound.postAuthCreds(
                apiModelToDTOMapper.toAuthCredsDTO(authCredsApiModel)
        );
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/authcreds/all")
    public ResponseEntity<List<AuthCredsDTO>> getAllAuthCreds() {
        List<AuthCredsDTO> authCredsList = authCredsInbound.getAllAuthCreds();
        if (!authCredsList.isEmpty()) {
            return ResponseEntity.ok(authCredsList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // GET method for retrieving UserProfileDTO
    @GetMapping("/userprofile/{username}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable String username) {
        UserProfileDTO userProfile = userProfileInbound.getUserProfile(username);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST method for creating UserProfileDTO
    @PostMapping("/userprofile")
    public ResponseEntity<Boolean> createUserProfile(@RequestBody UserProfileApiModel userProfile) {
        Boolean created = userProfileInbound.postUserProfile(
                apiModelToDTOMapper.toUserProfileDTO(userProfile)
        );
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

}
