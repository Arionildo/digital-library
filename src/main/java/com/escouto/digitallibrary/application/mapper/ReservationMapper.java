package com.escouto.digitallibrary.application.mapper;

import com.escouto.digitallibrary.domain.entity.Book;
import com.escouto.digitallibrary.domain.entity.Reservation;
import com.escouto.digitallibrary.domain.entity.User;
import com.escouto.digitallibrary.presentation.dto.ReservationDTO;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .userId(reservation.getUser().getId())
                .bookId(reservation.getBook().getId())
                .reservedAt(reservation.getReservedAt())
                .endDate(reservation.getEndDate())
                .build();
    }

    public Reservation toEntity(ReservationDTO dto) {
        return Reservation.builder()
                .id(dto.getId())
                .user(User.builder().id(dto.getUserId()).build())
                .book(Book.builder().id(dto.getBookId()).build())
                .reservedAt(dto.getReservedAt())
                .endDate(dto.getEndDate())
                .build();
    }
}