package com.fiap.meetingroom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private String responsavel;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    public Reserva() {
    }

    public Reserva(Sala sala, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String responsavel) {
        this.sala = sala;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.status = StatusReserva.ATIVA;
    }

    public Long getId() {
        return id;
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

    public Sala getSala() {
        return sala;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}