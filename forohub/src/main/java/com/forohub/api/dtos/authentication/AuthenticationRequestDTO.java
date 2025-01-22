package com.forohub.api.dtos.authentication;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(
    @NotBlank
    String username,
    @NotBlank
    String password
) {}
