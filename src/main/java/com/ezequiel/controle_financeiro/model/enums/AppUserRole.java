package com.ezequiel.controle_financeiro.model.enums;

import lombok.Getter;

public enum AppUserRole {
    ADMIN("ADMIN"),
    USER("USER");

    @Getter
    private String role;

    AppUserRole(String role) {
        this.role = role;
    }
}
