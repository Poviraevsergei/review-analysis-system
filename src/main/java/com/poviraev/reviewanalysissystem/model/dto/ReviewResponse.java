package com.poviraev.reviewanalysissystem.model.dto;

import java.util.UUID;

public record ReviewResponse(
        UUID id,
        String text,
        Integer rating,
        UUID userId,
        String location
) {
}
