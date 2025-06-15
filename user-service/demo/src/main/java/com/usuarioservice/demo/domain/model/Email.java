package com.usuarioservice.demo.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@Data
@NoArgsConstructor
public class Email {
    private String email;


    public Email(String email) {
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }

    public String getEmail() {
        return email;
    }
}