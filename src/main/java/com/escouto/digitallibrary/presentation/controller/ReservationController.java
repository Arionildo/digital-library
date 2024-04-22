package com.escouto.digitallibrary.presentation.controller;

import com.escouto.digitallibrary.application.service.ReservationService;
import com.escouto.digitallibrary.presentation.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservation Management")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Operation(summary = "Get all reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reservation by ID")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        Optional<ReservationDTO> reservation = reservationService.getReservationById(id);
        return reservation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new reservation")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO savedReservation = reservationService.saveReservation(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a reservation by ID")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationService.existsById(id)) {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}