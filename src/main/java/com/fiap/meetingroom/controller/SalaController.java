package com.fiap.meetingroom.controller;

import com.fiap.meetingroom.dto.SalaRequest;
import com.fiap.meetingroom.dto.SalaResponse;
import com.fiap.meetingroom.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public SalaResponse criar(@RequestBody @Valid SalaRequest request) {
        return salaService.criar(request);
    }

    @GetMapping
    public Page<SalaResponse> listar(Pageable pageable) {
        return salaService.listar(pageable);
    }

    @GetMapping("/{id}")
    public SalaResponse buscarPorId(@PathVariable Long id) {
        return salaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public SalaResponse atualizar(@PathVariable Long id, @RequestBody @Valid SalaRequest request) {
        return salaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        salaService.remover(id);
    }
}