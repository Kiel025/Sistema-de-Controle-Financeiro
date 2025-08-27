package com.ezequiel.controle_financeiro.dto;

import com.ezequiel.controle_financeiro.model.enums.AppUserRole;

import java.time.Instant;
import java.util.UUID;

public record AppUserResponseDTO(UUID id, String name, String email, AppUserRole userRole, Instant createdAt) {
}
