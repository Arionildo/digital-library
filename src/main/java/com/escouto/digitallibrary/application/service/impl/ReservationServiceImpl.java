package com.escouto.digitallibrary.application.service.impl;

import com.escouto.digitallibrary.application.mapper.ReservationMapper;
import com.escouto.digitallibrary.application.service.BookService;
import com.escouto.digitallibrary.application.service.ReservationService;
import com.escouto.digitallibrary.application.service.UserService;
import com.escouto.digitallibrary.domain.entity.Reservation;
import com.escouto.digitallibrary.domain.exception.BookNotFoundException;
import com.escouto.digitallibrary.domain.exception.OverlappingReservationException;
import com.escouto.digitallibrary.domain.exception.UserNotFoundException;
import com.escouto.digitallibrary.infrastructure.persistence.ReservationRepository;
import com.escouto.digitallibrary.presentation.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, BookService bookService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> getAllReservations() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .map(reservationMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservationDTO> getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDTO);
    }

    @Override
    @Transactional
    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        validateUser(reservationDTO.getUserId());
        validateBook(reservationDTO.getBookId(), reservationDTO.getReservedAt(), reservationDTO.getEndDate());

        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return reservationRepository.existsById(id);
    }

    private void validateUser(Long userId) {
        boolean userExists = userService.existsById(userId);
        if (!userExists) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }

    private void validateBook(Long bookId, LocalDateTime reservedAt, LocalDateTime endDate) {
        boolean bookExists = bookService.existsById(bookId);
        if (!bookExists) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }

        if (areReservationDatesOverlapping(bookId, reservedAt, endDate)) {
            throw new OverlappingReservationException("Reservation dates overlap with existing reservations");
        }
    }

    @Override
    public boolean areReservationDatesOverlapping(Long bookId, LocalDateTime reservedAt, LocalDateTime endDate) {
        return reservationRepository.existsByBookIdAndReservedAtBetweenOrEndDateBetween(bookId, reservedAt, endDate, reservedAt, endDate);
    }
}