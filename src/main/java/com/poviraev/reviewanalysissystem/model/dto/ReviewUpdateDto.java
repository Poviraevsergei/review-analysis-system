package com.poviraev.reviewanalysissystem.model.dto;

import java.util.UUID;

public record ReviewUpdateDto(
        String text,
        Integer rating,
        UUID userId,
        String location
) {
}
