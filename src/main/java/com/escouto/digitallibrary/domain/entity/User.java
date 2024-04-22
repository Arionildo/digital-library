package com.escouto.digitallibrary.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Builder
@Data
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    public User() {
        //
    }
}