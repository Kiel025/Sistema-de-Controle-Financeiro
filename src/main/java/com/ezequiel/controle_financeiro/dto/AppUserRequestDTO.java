package com.ezequiel.controle_financeiro.dto;

import com.ezequiel.controle_financeiro.model.enums.AppUserRole;

public record AppUserRequestDTO(String name, String email, String password, AppUserRole userRole) {
}
