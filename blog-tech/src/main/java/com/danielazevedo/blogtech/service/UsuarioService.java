package com.danielazevedo.blogtech.service;

import com.danielazevedo.blogtech.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.dto.response.UsuarioResponseDTO;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Cadastrar usuário:
    public Usuario cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        // Criar um USUARIO a partir do DTO:
        Usuario usuario = new Usuario(usuarioRequestDTO);

        // Criptografando a senha antes de persistir:
        String senha = usuario.getSenha();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = passwordEncoder.encode(senha);

        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);

        return usuario;
    }

    // Listar todos:
    public List<UsuarioResponseDTO> listarTodos() {

        List<UsuarioResponseDTO> usuarios = usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
        return usuarios;

    }
}
