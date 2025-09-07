package com.ezequiel.controle_financeiro.service;

import com.ezequiel.controle_financeiro.dto.AppUserRequestDTO;
import com.ezequiel.controle_financeiro.dto.AppUserResponseDTO;
import com.ezequiel.controle_financeiro.model.AppUser;
import com.ezequiel.controle_financeiro.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    public AppUserResponseDTO findById(UUID id) {
        AppUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error. User not found."));
        return new AppUserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getUserRole(), user.getCreatedAt());
    }

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public AppUserResponseDTO createUser(AppUserRequestDTO dto) {
        AppUser appUser = new AppUser();
        appUser.setName(dto.name());
        appUser.setEmail(dto.email());

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        appUser.setPasswordHash(encryptedPassword);
        appUser.setUserRole(dto.userRole());

        AppUser newAppUser = this.userRepository.save(appUser);

        return new AppUserResponseDTO(newAppUser.getId(), newAppUser.getName(), newAppUser.getEmail(), newAppUser.getUserRole(), newAppUser.getCreatedAt());
    }
}
