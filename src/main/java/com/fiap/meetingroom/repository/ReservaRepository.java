package com.fiap.meetingroom.repository;

import com.fiap.meetingroom.model.Reserva;
import com.fiap.meetingroom.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findBySalaIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
            Long salaId,
            StatusReserva status,
            LocalDateTime dataHoraFim,
            LocalDateTime dataHoraInicio
    );

    List<Reserva> findBySalaId(Long salaId);
}