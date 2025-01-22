package com.forohub.api.dtos.topic;

import java.time.LocalDateTime;

public record TopicResponseDTO(
    Long id,
    String title,
    String message,
    LocalDateTime creationDate,
    String status,
    String author,
    String course
) {}