package com.escouto.digitallibrary.application.mapper;

import com.escouto.digitallibrary.domain.entity.Book;
import com.escouto.digitallibrary.domain.entity.Review;
import com.escouto.digitallibrary.domain.entity.User;
import com.escouto.digitallibrary.presentation.dto.ReviewDTO;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDTO toDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .bookId(review.getBook().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public Review toEntity(ReviewDTO dto) {
        return Review.builder()
                .id(dto.getId())
                .user(User.builder().id(dto.getUserId()).build())
                .book(Book.builder().id(dto.getBookId()).build())
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}