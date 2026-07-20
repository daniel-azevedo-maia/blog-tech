package com.danielazevedo.blogtech.application.dto.response;

public record UsuarioResponseDTO(
        Long id,
        String nomeCompleto,
        String email,
        String login
) {
}
