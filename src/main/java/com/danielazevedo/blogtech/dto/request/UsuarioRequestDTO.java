package com.danielazevedo.blogtech.dto.request;

import com.danielazevedo.blogtech.model.Endereco;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String nome;
    private String sobrenome;
    private String email;
    private String login;
    private String senha;
    private Endereco endereco;

}