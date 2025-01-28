package com.danielazevedo.blogtech.application.service;

import com.danielazevedo.blogtech.application.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.application.dto.response.UsuarioResponseDTO;
import com.danielazevedo.blogtech.domain.model.Role;
import com.danielazevedo.blogtech.domain.model.Usuario;
import com.danielazevedo.blogtech.domain.repository.RoleRepository;
import com.danielazevedo.blogtech.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO);
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        usuario.getRoles().add(obterRolePadrao());
        return usuarioRepository.save(usuario);
    }

    public Usuario obterUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String login = authentication.getName();
            return usuarioRepository.findUserByLogin(login)
                    .orElseThrow(() -> new IllegalStateException("Usuário não encontrado!"));
        }
        throw new IllegalStateException("Usuário não autenticado!");
    }


    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public boolean verificarExistenciaLogin(String login) {
        return usuarioRepository.existsByLogin(login);
    }

    public String buscarNomePorLogin(String login) {
        return usuarioRepository.findNomeByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Nome não encontrado para o login fornecido!"));
    }

    public String obterNomeUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String login = authentication.getName();
            return buscarNomePorLogin(login);
        }
        throw new IllegalStateException("Usuário não autenticado!");
    }

    private Role obterRolePadrao() {
        return roleRepository.findByNomeRole("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER não encontrada!"));
    }

    private String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }
}
