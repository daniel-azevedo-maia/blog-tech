package com.danielazevedo.blogtech.application.dto.response;

public record ImagemPostDTO(
        byte[] conteudo,
        String tipoArquivo,
        String nomeArquivo
) {
}
