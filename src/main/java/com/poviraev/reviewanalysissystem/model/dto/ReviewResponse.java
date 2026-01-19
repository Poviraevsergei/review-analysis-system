package com.poviraev.reviewanalysissystem.model.dto;

import java.time.Instant;
import java.util.UUID;

public record ReviewResponse(
        UUID id,
        String text,
        Integer rating,
        String userId,
        String location,
        Instant createdAt,
        Instant updatedAt
) {}
