package com.usuarioservice.demo.domain.model;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public class Cpf {
    private String cpf;

    public Cpf() {}

    public Cpf(String cpf) {
        if (!isValidCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    private boolean isValidCpf(String cpf) {
        String regex = "\\d{11}";
        return Pattern.matches(regex, cpf);
    }

    public String getCpf() {
        return cpf;
    }
}