package com.poviraev.reviewanalysissystem.controller;

import com.poviraev.reviewanalysissystem.model.dto.ReviewCreateDto;
import com.poviraev.reviewanalysissystem.model.dto.ReviewPartialUpdateDto;
import com.poviraev.reviewanalysissystem.model.dto.ReviewResponse;
import com.poviraev.reviewanalysissystem.model.dto.ReviewUpdateDto;
import com.poviraev.reviewanalysissystem.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable UUID id) {
        ReviewResponse response = reviewService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ReviewResponse>> getAll(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {
        Page<ReviewResponse> page = reviewService.getAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewResponse> create(@RequestBody ReviewCreateDto createDto) {
        ReviewResponse response = reviewService.create(createDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody ReviewUpdateDto updateDto) {

        ReviewResponse response = reviewService.update(id, updateDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReviewResponse> partialUpdate(
            @PathVariable UUID id,
            @Valid @RequestBody ReviewPartialUpdateDto partialUpdateDto) {

        ReviewResponse response = reviewService.partialUpdate(id, partialUpdateDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

