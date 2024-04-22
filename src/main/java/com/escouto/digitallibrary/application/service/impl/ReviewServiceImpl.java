package com.escouto.digitallibrary.application.service.impl;

import com.escouto.digitallibrary.application.mapper.ReviewMapper;
import com.escouto.digitallibrary.application.service.ReviewService;
import com.escouto.digitallibrary.domain.entity.Review;
import com.escouto.digitallibrary.infrastructure.persistence.ReviewRepository;
import com.escouto.digitallibrary.presentation.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    @Transactional
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId).stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(reviewMapper::toDTO)
                .toList();
   }
}