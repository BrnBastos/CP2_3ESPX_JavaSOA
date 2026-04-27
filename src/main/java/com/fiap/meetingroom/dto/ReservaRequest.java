package com.fiap.meetingroom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReservaRequest {

    @NotNull(message = "Sala é obrigatória")
    private Long salaId;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "Data de fim é obrigatória")
    private LocalDateTime dataHoraFim;

    @NotBlank(message = "Responsável é obrigatório")
    private String responsavel;

    public Long getSalaId() {
        return salaId;
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

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
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
}