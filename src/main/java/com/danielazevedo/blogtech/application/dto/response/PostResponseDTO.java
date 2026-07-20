package com.danielazevedo.blogtech.application.dto.response;

import java.time.LocalDateTime;

public record PostResponseDTO(
        Long id,
        String nomeAutor,
        String titulo,
        String subtitulo,
        String categoria,
        String textoPrincipal,
        boolean possuiImagem,
        LocalDateTime dataPublicacao
) {
}
