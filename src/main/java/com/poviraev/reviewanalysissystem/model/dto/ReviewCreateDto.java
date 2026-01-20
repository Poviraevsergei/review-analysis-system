package com.poviraev.reviewanalysissystem.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ReviewCreateDto(
        @NotBlank String text,
        @Min(1) @Max(5) Integer rating,
        UUID userId,
        String location
) {
}
