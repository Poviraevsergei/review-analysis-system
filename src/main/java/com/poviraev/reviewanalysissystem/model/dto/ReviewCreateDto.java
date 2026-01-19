package com.poviraev.reviewanalysissystem.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewCreateDto(
        @NotBlank String text,
        @Min(1) @Max(5) Integer rating,
        String userId,
        String location
) {}
