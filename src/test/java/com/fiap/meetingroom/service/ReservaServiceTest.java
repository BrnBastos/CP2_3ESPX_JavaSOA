package com.fiap.meetingroom.service;

import com.fiap.meetingroom.dto.ReservaRequest;
import com.fiap.meetingroom.exception.BadRequestException;
import com.fiap.meetingroom.model.Reserva;
import com.fiap.meetingroom.model.Sala;
import com.fiap.meetingroom.model.StatusReserva;
import com.fiap.meetingroom.repository.ReservaRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    @Test
    void deveBloquearReservaComDataFinalAntesDaInicial() {
        ReservaRepository reservaRepository = mock(ReservaRepository.class);
        SalaService salaService = mock(SalaService.class);

        ReservaService reservaService = new ReservaService(reservaRepository, salaService);

        ReservaRequest request = new ReservaRequest();
        request.setSalaId(1L);
        request.setResponsavel("Bruno");
        request.setDataHoraInicio(LocalDateTime.of(2026, 4, 27, 10, 0));
        request.setDataHoraFim(LocalDateTime.of(2026, 4, 27, 9, 0));

        assertThrows(BadRequestException.class, () -> reservaService.criar(request));
    }

    @Test
    void deveBloquearReservaComConflitoDeHorario() {
        ReservaRepository reservaRepository = mock(ReservaRepository.class);
        SalaService salaService = mock(SalaService.class);

        ReservaService reservaService = new ReservaService(reservaRepository, salaService);

        Sala sala = new Sala("Sala Azul", 10, "Bloco A");
        sala.setId(1L);

        Reserva reservaExistente = new Reserva(
                sala,
                LocalDateTime.of(2026, 4, 27, 10, 0),
                LocalDateTime.of(2026, 4, 27, 11, 0),
                "Maria"
        );

        reservaExistente.setId(1L);
        reservaExistente.setStatus(StatusReserva.ATIVA);

        ReservaRequest request = new ReservaRequest();
        request.setSalaId(1L);
        request.setResponsavel("Bruno");
        request.setDataHoraInicio(LocalDateTime.of(2026, 4, 27, 10, 30));
        request.setDataHoraFim(LocalDateTime.of(2026, 4, 27, 11, 30));

        when(salaService.buscarSalaEntity(1L)).thenReturn(sala);

        when(reservaRepository.findBySalaIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                eq(1L),
                eq(StatusReserva.ATIVA),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        )).thenReturn(List.of(reservaExistente));

        assertThrows(BadRequestException.class, () -> reservaService.criar(request));
    }
}