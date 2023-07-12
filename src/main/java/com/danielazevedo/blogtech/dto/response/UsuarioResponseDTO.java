package com.danielazevedo.blogtech.dto.response;

import com.danielazevedo.blogtech.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public record UsuarioResponseDTO(
        String nome,
        String sobrenome,
        String email)
{
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail());
    }
}