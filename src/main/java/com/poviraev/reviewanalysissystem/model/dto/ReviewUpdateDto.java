package com.poviraev.reviewanalysissystem.model.dto;

import java.util.Map;

public record ReviewUpdateDto(
        String text,
        Integer rating,
        ReviewStatus status
) {}
