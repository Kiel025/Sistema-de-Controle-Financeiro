package com.ezequiel.controle_financeiro.controller;

import com.ezequiel.controle_financeiro.dto.AppUserResponseDTO;
import com.ezequiel.controle_financeiro.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/${id}")
    public ResponseEntity<AppUserResponseDTO> getById(@PathVariable UUID id) {
        AppUserResponseDTO userDTO = appUserService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

}
