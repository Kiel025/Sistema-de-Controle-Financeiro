package com.ezequiel.controle_financeiro.security.config;

import com.ezequiel.controle_financeiro.model.AppUser;
import com.ezequiel.controle_financeiro.security.model.AppUserDetails;
import com.ezequiel.controle_financeiro.security.service.TokenService;
import com.ezequiel.controle_financeiro.service.AppUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AppUserService appUserService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException, UsernameNotFoundException {
         var token = this.recoverToken(request);
         if (token != null) {
             var subject = this.tokenService.validateToken(token);
             AppUser user = this.appUserService.findByEmail(subject).orElseThrow(() -> new UsernameNotFoundException("User not found."));
             AppUserDetails userDetails = new AppUserDetails(user);

             var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(authentication);
         }
         filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer", "");
    }
}
