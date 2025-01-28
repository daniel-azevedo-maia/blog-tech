package com.danielazevedo.blogtech.application.dto.request;

import com.danielazevedo.blogtech.domain.model.Endereco;
import lombok.*;

@Getter @Setter
public class UsuarioRequestDTO {

    private String nome;
    private String sobrenome;
    private String email;
    private String login;
    private String senha;
    private Endereco endereco;

}