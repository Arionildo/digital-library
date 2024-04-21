package com.escouto.digitallibrary.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public final class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int rating;

    @Column(length = 500)
    private String comment;
}