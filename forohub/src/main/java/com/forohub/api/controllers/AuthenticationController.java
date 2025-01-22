package com.forohub.api.controllers;

import com.forohub.api.dtos.authentication.AuthenticationRequestDTO;
import com.forohub.api.dtos.authentication.AuthenticationResponseDTO;
import com.forohub.api.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody @Valid AuthenticationRequestDTO data
    ) {
        var token = authenticationService.authenticate(data);
        return ResponseEntity.ok(token);
    }
}
