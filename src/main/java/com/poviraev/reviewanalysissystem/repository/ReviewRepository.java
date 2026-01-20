package com.poviraev.reviewanalysissystem.repository;

import com.poviraev.reviewanalysissystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
