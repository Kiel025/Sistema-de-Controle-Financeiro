package com.ezequiel.controle_financeiro.service;

import com.ezequiel.controle_financeiro.dto.AppUserResponseDTO;
import com.ezequiel.controle_financeiro.model.AppUser;
import com.ezequiel.controle_financeiro.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    public AppUserResponseDTO findById(UUID id) {
        AppUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error. User not found."));
        return new AppUserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
    }

}
