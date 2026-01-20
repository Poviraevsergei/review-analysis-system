package com.poviraev.reviewanalysissystem.service;

import com.poviraev.reviewanalysissystem.exception.ReviewNotFoundException;
import com.poviraev.reviewanalysissystem.mapper.ReviewMapper;
import com.poviraev.reviewanalysissystem.model.Review;
import com.poviraev.reviewanalysissystem.model.dto.ReviewCreateDto;
import com.poviraev.reviewanalysissystem.model.dto.ReviewPartialUpdateDto;
import com.poviraev.reviewanalysissystem.model.dto.ReviewResponse;
import com.poviraev.reviewanalysissystem.model.dto.ReviewUpdateDto;
import com.poviraev.reviewanalysissystem.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public Page<ReviewResponse> getAll(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return reviewMapper.pageReviewToResponse(reviews);
    }

    public ReviewResponse getById(UUID uuid) {
        Optional<Review> reviews = reviewRepository.findById(uuid);
        return reviews.map(reviewMapper::convertToResponse).orElseThrow(() -> new ReviewNotFoundException(uuid));
    }

    public void delete(UUID id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException(id);
        }
        reviewRepository.deleteById(id);
    }

    public ReviewResponse create(ReviewCreateDto reviewCreateDto) {
        Review review = reviewMapper.convertToReview(reviewCreateDto);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.convertToResponse(savedReview);
    }

    private Review applyUpdates(Review review, Consumer<Review> updateLogic) {
        updateLogic.accept(review);
        review.setUpdatedAt(Instant.now());
        return reviewRepository.save(review);
    }

    public ReviewResponse update(UUID id, ReviewUpdateDto reviewUpdateDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        if (reviewUpdateDto.userId() != null &&
                !reviewUpdateDto.userId().equals(review.getUserId())) {
            // TODO: admin?
            //if (false) {
            //    throw new AccessDeniedException("Only admin can change review author");
            //}
        }

        Review updated = applyUpdates(review, r -> {
            Optional.ofNullable(reviewUpdateDto.text()).ifPresent(r::setText);
            Optional.ofNullable(reviewUpdateDto.rating()).ifPresent(r::setRating);
            Optional.ofNullable(reviewUpdateDto.location()).ifPresent(r::setLocation);
            Optional.ofNullable(reviewUpdateDto.userId()).ifPresent(r::setUserId);
        });

        return reviewMapper.convertToResponse(updated);
    }

    public ReviewResponse partialUpdate(UUID id, ReviewPartialUpdateDto dto) {
        if (dto.text() == null && dto.rating() == null && dto.location() == null) {
            throw new IllegalArgumentException("At least one field must be provided for partial update");
        }

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        Review updated = applyUpdates(review, r -> {
            Optional.ofNullable(dto.text()).ifPresent(r::setText);
            Optional.ofNullable(dto.rating()).ifPresent(r::setRating);
            Optional.ofNullable(dto.location()).ifPresent(r::setLocation);
        });

        return reviewMapper.convertToResponse(updated);
    }
}
