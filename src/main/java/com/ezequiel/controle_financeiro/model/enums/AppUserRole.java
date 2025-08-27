package com.ezequiel.controle_financeiro.model.enums;

import lombok.Getter;

public enum AppUserRole {
    ADMIN("admin"),
    USER("user"),
    MANAGER("manager"),
    READ_ONLY("read_only");

    @Getter
    private String role;

    AppUserRole(String role) {
        this.role = role;
    }
}
