package com.escouto.digitallibrary.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long bookId;

    @Min(value = 0, message = "Rating must be between 0 and 10")
    @Max(value = 10, message = "Rating must be between 0 and 10")
    private int rating;

    @NotBlank(message = "Comment cannot be blank")
    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;

    private LocalDateTime createdAt;
}