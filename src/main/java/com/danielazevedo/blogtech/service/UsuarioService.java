package com.danielazevedo.blogtech.service;

import com.danielazevedo.blogtech.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.dto.response.UsuarioResponseDTO;
import com.danielazevedo.blogtech.model.Role;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.RoleRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Cadastrar usuário:
    public Usuario cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        // Criar um USUARIO a partir do DTO:
        Usuario usuario = new Usuario(usuarioRequestDTO);

        // Criptografando a senha antes de persistir:
        String senha = usuario.getSenha();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = passwordEncoder.encode(senha);
        usuario.setSenha(senhaCriptografada);

        // Recebendo o papel de USER
        Optional<Role> papel = roleRepository.findById(2L);

        if(papel.get() == null) {
            throw new IllegalStateException("O papel USER não foi encontrado");
        }

        usuario.getRoles().add(papel.get());

        usuarioRepository.save(usuario);

        return usuario;
    }

    // Listar todos:
    public List<UsuarioResponseDTO> listarTodos() {

        List<UsuarioResponseDTO> usuarios = usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
        return usuarios;
    }

    // Verifica existência:
    public boolean verificarExistenciaLogin(String login) {
        return usuarioRepository.existsByLogin(login);

    }


}
