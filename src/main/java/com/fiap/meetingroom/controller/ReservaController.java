package com.fiap.meetingroom.controller;

import com.fiap.meetingroom.dto.ReservaRequest;
import com.fiap.meetingroom.dto.ReservaResponse;
import com.fiap.meetingroom.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ReservaResponse criar(@RequestBody @Valid ReservaRequest request) {
        return reservaService.criar(request);
    }

    @GetMapping
    public List<ReservaResponse> listar(@RequestParam(required = false) Long salaId) {
        return reservaService.listar(salaId);
    }

    @PatchMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Long id) {
        reservaService.cancelar(id);
    }
}