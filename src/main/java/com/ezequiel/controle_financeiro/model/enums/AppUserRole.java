package com.ezequiel.controle_financeiro.model.enums;

import lombok.Getter;

public enum AppUserRole {
    ADMIN("admin"),
    USER("user");

    @Getter
    private String role;

    AppUserRole(String role) {
        this.role = role;
    }
}
