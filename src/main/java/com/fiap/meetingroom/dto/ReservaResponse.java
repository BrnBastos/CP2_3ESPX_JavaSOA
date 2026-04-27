package com.fiap.meetingroom.dto;

import com.fiap.meetingroom.model.Reserva;
import com.fiap.meetingroom.model.StatusReserva;

import java.time.LocalDateTime;

public class ReservaResponse {

    private Long id;
    private Long salaId;
    private String salaNome;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String responsavel;
    private StatusReserva status;

    public ReservaResponse(Reserva reserva) {
        this.id = reserva.getId();
        this.salaId = reserva.getSala().getId();
        this.salaNome = reserva.getSala().getNome();
        this.dataHoraInicio = reserva.getDataHoraInicio();
        this.dataHoraFim = reserva.getDataHoraFim();
        this.responsavel = reserva.getResponsavel();
        this.status = reserva.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Long getSalaId() {
        return salaId;
    }

    public String getSalaNome() {
        return salaNome;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public StatusReserva getStatus() {
        return status;
    }
}