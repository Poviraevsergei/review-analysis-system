package com.poviraev.reviewanalysissystem.service;

import com.poviraev.reviewanalysissystem.model.dto.ReviewCreateDto;
import com.poviraev.reviewanalysissystem.model.dto.ReviewPartialUpdateDto;
import com.poviraev.reviewanalysissystem.model.dto.ReviewResponse;
import com.poviraev.reviewanalysissystem.model.dto.ReviewUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {

    public Page<ReviewResponse> getAll(Pageable pageable) {
        return null; //TODO: implement logic
    }

    public ReviewResponse getById(UUID uuid) {
        return null; //TODO: implement logic
    }

    public void delete(UUID id) {
        //TODO: implement logic
    }

    public ReviewResponse create(ReviewCreateDto reviewCreateDto) {
        return null; //TODO: implement logic
    }

    public ReviewResponse update(UUID id, ReviewUpdateDto reviewUpdateDto) {
        return null; //TODO: implement logic
    }

    public ReviewResponse partialUpdate(UUID id, ReviewPartialUpdateDto reviewPartialUpdateDto) {
        return null; //TODO: implement logic
    }
}
