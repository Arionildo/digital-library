package com.escouto.digitallibrary.presentation.controller;

import com.escouto.digitallibrary.application.service.ReviewService;
import com.escouto.digitallibrary.presentation.dto.ReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Review Management")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @Operation(summary = "Get all reviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-book/{bookId}")
    @Operation(summary = "Get reviews by book ID")
    public ResponseEntity<List<ReviewDTO>> getReviewsByBookId(@PathVariable Long bookId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByBookId(bookId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by-user/{userId}")
    @Operation(summary = "Get reviews by user ID")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    @Operation(summary = "Create a new review")
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.saveReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }
}