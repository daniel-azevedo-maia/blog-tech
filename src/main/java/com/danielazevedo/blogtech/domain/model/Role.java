package com.danielazevedo.blogtech.domain.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "O nome do papel é obrigatório.")
    @Column(name = "nome_role", nullable = false, unique = true)
    private String nomeRole;

    @Override
    public String getAuthority() {
        return nomeRole;
    }
}
