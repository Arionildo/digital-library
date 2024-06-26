package com.escouto.digitallibrary.application.service;

import com.escouto.digitallibrary.presentation.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO saveReview(ReviewDTO reviewDTO);
    List<ReviewDTO> getAllReviews();
    List<ReviewDTO> getReviewsByBookId(Long bookId);
    List<ReviewDTO> getReviewsByUserId(Long userId);
}