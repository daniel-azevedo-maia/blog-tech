package com.danielazevedo.blogtech.application.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter
public class PostDTO {

    private String titulo;
    private String subtitulo;
    private String categoria;
    private String textoPrincipal;
    private MultipartFile imagemPost;
    private LocalDateTime dataPublicacao;

}