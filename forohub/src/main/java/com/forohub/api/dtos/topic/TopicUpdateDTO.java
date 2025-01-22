package com.forohub.api.dtos.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicUpdateDTO(
    @NotBlank
    String title,
    @NotBlank
    String message
) {}
