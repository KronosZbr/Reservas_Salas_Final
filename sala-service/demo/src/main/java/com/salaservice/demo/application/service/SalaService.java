package com.salaservice.demo.application.service;

import com.salaservice.demo.domain.model.Sala;
import com.salaservice.demo.domain.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    public List<Sala> listar() {
        return repository.findAll();
    }

    public Sala salvar(Sala sala) {
        return repository.save(sala);
    }

    public Sala atualizar(Long id, Sala salaDetails) {
        Sala sala = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sala com ID " + id + " não encontrada."));

        sala.setNome(salaDetails.getNome());
        sala.setCapacidade(salaDetails.getCapacidade());

        return repository.save(sala);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Sala com ID " + id + " não encontrada.");
        }
        repository.deleteById(id);
    }
}