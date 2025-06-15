package com.salaservice.demo.infrastructure.controller;

import com.salaservice.demo.domain.model.Sala;
import com.salaservice.demo.application.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    public List<Sala> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala criar(@RequestBody Sala sala) {
        return service.salvar(sala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> atualizarSala(@PathVariable Long id, @RequestBody Sala salaDetails) {
        Sala updatedSala = service.atualizar(id, salaDetails);
        return ResponseEntity.ok(updatedSala);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarSala(@PathVariable Long id) {
        service.deletar(id);
    }
}