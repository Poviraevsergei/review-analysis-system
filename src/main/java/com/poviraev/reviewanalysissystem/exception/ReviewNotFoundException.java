package com.poviraev.reviewanalysissystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(UUID id) {
        super(String.format("Review not found with id: %s", id));
    }
}
