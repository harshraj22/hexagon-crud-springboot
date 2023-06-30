package com.example.Profile.adaptor.inbound.controller;


import com.example.Profile.domain.dto.AuthCredsDTO;
import com.example.Profile.domain.dto.UserProfileDTO;
import com.example.Profile.domain.service.AuthCredsInboundService;
import com.example.Profile.domain.service.UserProfileInboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    AuthCredsInboundService authCredsInboundService;

    @Autowired
    UserProfileInboundService userProfileInboundService;

    // GET method for retrieving AuthCredsDTO
    @GetMapping("/authcreds/{username}")
    public ResponseEntity<AuthCredsDTO> getAuthCreds(@PathVariable String username) {
        AuthCredsDTO authCreds = authCredsInboundService.getAuthCreds(username);
        if (authCreds != null) {
            return ResponseEntity.ok(authCreds);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST method for creating AuthCredsDTO
    @PostMapping("/authcreds")
    public ResponseEntity<Boolean> createAuthCreds(@RequestBody AuthCredsDTO authCreds) {
        Boolean created = authCredsInboundService.postAuthCreds(authCreds);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }    }

    // GET method for retrieving UserProfileDTO
    @GetMapping("/userprofile/{username}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable String username) {
        UserProfileDTO userProfile = userProfileInboundService.getUserProfile(username);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST method for creating UserProfileDTO
    @PostMapping("/userprofile")
    public ResponseEntity<Boolean> createUserProfile(@RequestBody UserProfileDTO userProfile) {
        Boolean created = userProfileInboundService.postUserProfile(userProfile);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

}
