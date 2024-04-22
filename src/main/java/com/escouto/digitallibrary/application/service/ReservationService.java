package com.escouto.digitallibrary.application.service;

import com.escouto.digitallibrary.presentation.dto.ReservationDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<ReservationDTO> getAllReservations();

    Optional<ReservationDTO> getReservationById(Long id);

    ReservationDTO saveReservation(ReservationDTO reservationDTO);

    void deleteReservation(Long id);

    boolean existsById(Long id);

    /**
     * Check if the requested reservation dates overlap with any existing reservations for the book.
     *
     * @param bookId        the ID of the book to check
     * @param reservedAt    the start date of the requested reservation
     * @param endDate       the end date of the requested reservation
     * @return true if the requested reservation dates overlap with existing reservations, false otherwise
     */
    boolean areReservationDatesOverlapping(Long bookId, LocalDateTime reservedAt, LocalDateTime endDate);
}
