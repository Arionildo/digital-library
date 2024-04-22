package com.escouto.digitallibrary.infrastructure.persistence;

import com.escouto.digitallibrary.domain.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    boolean existsByBookIdAndReservedAtBetweenOrEndDateBetween(
            Long bookId, LocalDateTime start, LocalDateTime end,
            LocalDateTime start2, LocalDateTime end2
    );
}