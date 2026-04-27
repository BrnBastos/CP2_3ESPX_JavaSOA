package com.fiap.meetingroom.service;

import com.fiap.meetingroom.dto.SalaRequest;
import com.fiap.meetingroom.dto.SalaResponse;
import com.fiap.meetingroom.exception.NotFoundException;
import com.fiap.meetingroom.model.Sala;
import com.fiap.meetingroom.repository.SalaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public SalaResponse criar(SalaRequest request) {
        Sala sala = new Sala(
                request.getNome(),
                request.getCapacidade(),
                request.getLocalizacao()
        );

        Sala salaSalva = salaRepository.save(sala);

        return new SalaResponse(salaSalva);
    }

    public Page<SalaResponse> listar(Pageable pageable) {
        return salaRepository.findAll(pageable)
                .map(SalaResponse::new);
    }

    public SalaResponse buscarPorId(Long id) {
        Sala sala = buscarSalaEntity(id);
        return new SalaResponse(sala);
    }

    public SalaResponse atualizar(Long id, SalaRequest request) {
        Sala sala = buscarSalaEntity(id);

        sala.setNome(request.getNome());
        sala.setCapacidade(request.getCapacidade());
        sala.setLocalizacao(request.getLocalizacao());

        Sala salaAtualizada = salaRepository.save(sala);

        return new SalaResponse(salaAtualizada);
    }

    public void remover(Long id) {
        Sala sala = buscarSalaEntity(id);
        salaRepository.delete(sala);
    }

    public Sala buscarSalaEntity(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sala não encontrada"));
    }
}