package com.usuarioservice.demo.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String cep;

    public Endereco(String rua, String numero, String cidade, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }
}