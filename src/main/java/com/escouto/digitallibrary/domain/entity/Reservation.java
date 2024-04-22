package com.escouto.digitallibrary.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Builder
@Data
public final class Reservation {
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
    private LocalDateTime reservedAt;

    @Column(nullable = false)
    private LocalDateTime endDate;

    public Reservation() {
        //
    }
}