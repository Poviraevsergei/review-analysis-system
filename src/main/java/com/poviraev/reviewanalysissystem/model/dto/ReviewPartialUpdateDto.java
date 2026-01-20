package com.poviraev.reviewanalysissystem.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record ReviewPartialUpdateDto(
        @Size(max = 1000) String text,
        @Min(1) @Max(5) Integer rating,
        String location
) {
}
