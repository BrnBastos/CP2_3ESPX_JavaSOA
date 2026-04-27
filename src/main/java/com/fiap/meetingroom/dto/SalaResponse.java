package com.fiap.meetingroom.dto;

import com.fiap.meetingroom.model.Sala;

public class SalaResponse {

    private Long id;
    private String nome;
    private Integer capacidade;
    private String localizacao;

    public SalaResponse(Sala sala) {
        this.id = sala.getId();
        this.nome = sala.getNome();
        this.capacidade = sala.getCapacidade();
        this.localizacao = sala.getLocalizacao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }
}