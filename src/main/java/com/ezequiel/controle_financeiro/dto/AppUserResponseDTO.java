package com.ezequiel.controle_financeiro.dto;

import java.time.Instant;
import java.util.UUID;

public record AppUserResponseDTO(UUID id, String name, String email, Instant createdAt) {
}
