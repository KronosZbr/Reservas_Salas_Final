package com.usuarioservice.demo.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Nome nome;

    @Embedded
    private Email email;

    private String senha;

    @Embedded
    private Telefone telefone;

    @Embedded
    private Endereco endereco;

    @Embedded
    private Cpf cpf;

    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
}