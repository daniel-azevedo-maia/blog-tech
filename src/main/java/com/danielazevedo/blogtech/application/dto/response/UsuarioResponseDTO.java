package com.danielazevedo.blogtech.application.dto.response;

import com.danielazevedo.blogtech.domain.model.Usuario;

public record UsuarioResponseDTO(
        String nome,
        String sobrenome,
        String email)
{
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail());
    }
}