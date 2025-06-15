package com.reservaservice.demo.application.service;

import com.reservaservice.demo.domain.model.Reserva;
import com.reservaservice.demo.domain.repository.ReservaRepository;
import com.reservaservice.demo.domain.repository.ReservaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository repository;

    @Autowired
    private ReservaUserRepository userRepository;

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Reserva salvar(Reserva reserva) {
        userRepository.findByExternalUserId(reserva.getUsuarioId().longValue())
                .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + reserva.getUsuarioId() + " não encontrado."));

        boolean conflito = repository.existsBySalaIdAndDataHora(reserva.getSalaId(), reserva.getDataHora());
        if (conflito) {
            throw new IllegalStateException("Esta sala já está reservada para o horário solicitado.");
        }
        
        return repository.save(reserva);
    }

    public Reserva atualizar(Long id, Reserva reservaDetails) {
        Reserva reserva = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva com ID " + id + " não encontrada."));

        // Valida se o novo horário não entra em conflito (excluindo a própria reserva da verificação)
        boolean conflito = repository.findAll().stream()
                .anyMatch(r -> !r.getId().equals(id) &&
                                r.getSalaId().equals(reservaDetails.getSalaId()) &&
                                r.getDataHora().equals(reservaDetails.getDataHora()));
        if (conflito) {
            throw new IllegalStateException("Esta sala já está reservada para o novo horário solicitado.");
        }

        reserva.setDataHora(reservaDetails.getDataHora());
        reserva.setSalaId(reservaDetails.getSalaId());
        reserva.setUsuarioId(reservaDetails.getUsuarioId());

        return repository.save(reserva);
    }
    
    public void cancelar(Long reservaId) {
        if (!repository.existsById(reservaId)) {
            throw new IllegalArgumentException("Reserva com ID " + reservaId + " não encontrada.");
        }
        repository.deleteById(reservaId);
    }
}