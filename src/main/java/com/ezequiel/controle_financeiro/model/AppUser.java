package com.ezequiel.controle_financeiro.model;

import com.ezequiel.controle_financeiro.model.enums.AppUserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 160)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "role", columnDefinition = "user_role")
    private AppUserRole userRole;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = Instant.now();

        if (this.userRole == null) this.userRole = AppUserRole.USER;
    }
}
