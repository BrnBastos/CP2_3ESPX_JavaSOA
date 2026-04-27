package com.fiap.meetingroom.service;

import com.fiap.meetingroom.dto.ReservaRequest;
import com.fiap.meetingroom.dto.ReservaResponse;
import com.fiap.meetingroom.exception.BadRequestException;
import com.fiap.meetingroom.exception.NotFoundException;
import com.fiap.meetingroom.model.Reserva;
import com.fiap.meetingroom.model.Sala;
import com.fiap.meetingroom.model.StatusReserva;
import com.fiap.meetingroom.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaService salaService;

    public ReservaService(ReservaRepository reservaRepository, SalaService salaService) {
        this.reservaRepository = reservaRepository;
        this.salaService = salaService;
    }

    public ReservaResponse criar(ReservaRequest request) {
        validarDatas(request);

        Sala sala = salaService.buscarSalaEntity(request.getSalaId());

        boolean existeConflito = !reservaRepository
                .findBySalaIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                        request.getSalaId(),
                        StatusReserva.ATIVA,
                        request.getDataHoraFim(),
                        request.getDataHoraInicio()
                )
                .isEmpty();

        if (existeConflito) {
            throw new BadRequestException("Já existe uma reserva ativa para essa sala nesse horário");
        }

        Reserva reserva = new Reserva(
                sala,
                request.getDataHoraInicio(),
                request.getDataHoraFim(),
                request.getResponsavel()
        );

        Reserva reservaSalva = reservaRepository.save(reserva);

        return new ReservaResponse(reservaSalva);
    }

    public List<ReservaResponse> listar(Long salaId) {
        List<Reserva> reservas;

        if (salaId != null) {
            reservas = reservaRepository.findBySalaId(salaId);
        } else {
            reservas = reservaRepository.findAll();
        }

        return reservas.stream()
                .map(ReservaResponse::new)
                .toList();
    }

    public void cancelar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva não encontrada"));

        reserva.setStatus(StatusReserva.CANCELADA);

        reservaRepository.save(reserva);
    }

    private void validarDatas(ReservaRequest request) {
        if (!request.getDataHoraFim().isAfter(request.getDataHoraInicio())) {
            throw new BadRequestException("Data final deve ser depois da data inicial");
        }
    }
}