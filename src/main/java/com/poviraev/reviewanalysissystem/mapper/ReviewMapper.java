package com.poviraev.reviewanalysissystem.mapper;

import com.poviraev.reviewanalysissystem.model.Review;
import com.poviraev.reviewanalysissystem.model.dto.ReviewCreateDto;
import com.poviraev.reviewanalysissystem.model.dto.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ReviewMapper {
    public Page<ReviewResponse> pageReviewToResponse(Page<Review> reviews) {
        return reviews.map(this::convertToResponse);
    }

    public ReviewResponse convertToResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getText(),
                review.getRating(),
                review.getUserId(),
                review.getLocation()
        );
    }

    public Review convertToReview(ReviewCreateDto reviewCreateDto) {
        return new Review(UUID.randomUUID(),
                reviewCreateDto.text(),
                reviewCreateDto.rating(),
                reviewCreateDto.userId(),
                reviewCreateDto.location(),
                Instant.now(),
                Instant.now());
    }
}
