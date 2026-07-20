package com.danielazevedo.blogtech.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class PostRequestDTO {

    @NotBlank(message = "Informe o título.")
    @Size(max = 180, message = "O título deve possuir no máximo 180 caracteres.")
    private String titulo;

    @NotBlank(message = "Informe o subtítulo.")
    @Size(max = 300, message = "O subtítulo deve possuir no máximo 300 caracteres.")
    private String subtitulo;

    @NotBlank(message = "Escolha uma categoria.")
    private String categoria;

    @NotBlank(message = "Escreva o conteúdo da publicação.")
    @Size(max = 100_000, message = "O texto excedeu o limite permitido.")
    private String textoPrincipal;

    private MultipartFile imagemPost;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTextoPrincipal() {
        return textoPrincipal;
    }

    public void setTextoPrincipal(String textoPrincipal) {
        this.textoPrincipal = textoPrincipal;
    }

    public MultipartFile getImagemPost() {
        return imagemPost;
    }

    public void setImagemPost(MultipartFile imagemPost) {
        this.imagemPost = imagemPost;
    }
}
