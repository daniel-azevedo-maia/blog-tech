package com.danielazevedo.blogtech.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable {

    public static final String ROLE_USER = "ROLE_USER";

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_role", nullable = false, unique = true, length = 50)
    private String nomeRole;

    public Role() {
    }

    public Role(String nomeRole) {
        this.nomeRole = Objects.requireNonNull(nomeRole);
    }

    public Long getId() {
        return id;
    }

    public String getNomeRole() {
        return nomeRole;
    }

    @Override
    public String getAuthority() {
        return nomeRole;
    }
}
