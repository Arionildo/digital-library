package com.escouto.digitallibrary.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDateTime reservedAt;
    private LocalDateTime endDate;
}