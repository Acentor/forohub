package com.forohub.api.dtos.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicRequestDTO(
    @NotBlank
    String title,
    @NotBlank
    String message,
    @NotBlank
    String author,
    @NotBlank
    String course
) {}