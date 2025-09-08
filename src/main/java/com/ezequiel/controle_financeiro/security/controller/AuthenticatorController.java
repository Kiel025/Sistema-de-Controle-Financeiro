package com.ezequiel.controle_financeiro.security.controller;

import com.ezequiel.controle_financeiro.dto.AppUserRequestDTO;
import com.ezequiel.controle_financeiro.dto.AppUserResponseDTO;
import com.ezequiel.controle_financeiro.security.model.AppUserDetails;
import com.ezequiel.controle_financeiro.security.model.dto.LoginRequestDTO;
import com.ezequiel.controle_financeiro.security.model.dto.LoginResponseDTO;
import com.ezequiel.controle_financeiro.security.service.TokenService;
import com.ezequiel.controle_financeiro.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid LoginRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((AppUserDetails) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AppUserResponseDTO> register(@RequestBody @Valid AppUserRequestDTO dto) {
        if(this.appUserService.findByEmail(dto.email()).isPresent()) return ResponseEntity.badRequest().build();

        AppUserResponseDTO appUserResponseDTO = this.appUserService.createUser(dto);
        return ResponseEntity.status(201).body(appUserResponseDTO);
    }
}
