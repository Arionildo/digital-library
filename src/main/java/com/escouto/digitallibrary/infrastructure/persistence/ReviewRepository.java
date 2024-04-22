package com.escouto.digitallibrary.infrastructure.persistence;

import com.escouto.digitallibrary.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Collection<Review> findByBookId(Long bookId);
    Collection<Review> findByUserId(Long userId);
}