package com.reservaservice.demo.infrastructure.controller;

import com.reservaservice.demo.domain.model.Reserva;
import com.reservaservice.demo.application.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) {
        Reserva savedReserva = reservaService.salvar(reserva);
        return new ResponseEntity<>(savedReserva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Reserva updatedReserva = reservaService.atualizar(id, reservaDetails);
        return ResponseEntity.ok(updatedReserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarReserva(@PathVariable Long id) {
        reservaService.cancelar(id);
    }
}