package com.reservaservice.demo.domain.repository;

import com.reservaservice.demo.domain.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Novo método para checar conflitos de reserva
    boolean existsBySalaIdAndDataHora(Integer salaId, LocalDateTime dataHora);
}