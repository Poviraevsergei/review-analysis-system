package com.poviraev.reviewanalysissystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Review {
    @Id
    private UUID id;
    private String text;
    private Integer rating;
    private UUID userId;
    private String location;

    @Column(name = "created")
    private Instant createdAt;

    @Column(name = "updated")
    private Instant updatedAt;
}
