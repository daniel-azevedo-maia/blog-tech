package com.danielazevedo.blogtech.application.service;

import com.danielazevedo.blogtech.application.dto.request.EnderecoRequestDTO;
import com.danielazevedo.blogtech.application.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.application.dto.response.UsuarioResponseDTO;
import com.danielazevedo.blogtech.application.exception.RecursoNaoEncontradoException;
import com.danielazevedo.blogtech.application.exception.RegraDeNegocioException;
import com.danielazevedo.blogtech.domain.model.Endereco;
import com.danielazevedo.blogtech.domain.model.Role;
import com.danielazevedo.blogtech.domain.model.Usuario;
import com.danielazevedo.blogtech.domain.repository.RoleRepository;
import com.danielazevedo.blogtech.domain.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO request) {
        validarCadastro(request);

        Endereco endereco = converterEndereco(request.getEndereco());
        Usuario usuario = new Usuario(
                limpar(request.getNome()),
                limpar(request.getSobrenome()),
                normalizarEmail(request.getEmail()),
                normalizarLogin(request.getLogin()),
                passwordEncoder.encode(request.getSenha()),
                endereco
        );
        usuario.adicionarRole(obterRolePadrao());

        try {
            return converter(usuarioRepository.save(usuario));
        } catch (DataIntegrityViolationException exception) {
            throw new RegraDeNegocioException("Já existe um usuário com esse login ou e-mail.");
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorLogin(String login) {
        return usuarioRepository.findByLoginIgnoreCase(normalizarLogin(login))
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return usuarioRepository.findAllByOrderByNomeAscSobrenomeAsc()
                .stream()
                .map(this::converter)
                .toList();
    }

    @Transactional(readOnly = true)
    public String buscarNomePorLogin(String login) {
        return buscarPorLogin(login).getNome();
    }

    @Transactional(readOnly = true)
    public boolean loginJaExiste(String login) {
        return login != null && usuarioRepository.existsByLoginIgnoreCase(normalizarLogin(login));
    }

    @Transactional(readOnly = true)
    public boolean emailJaExiste(String email) {
        return email != null && usuarioRepository.existsByEmailIgnoreCase(normalizarEmail(email));
    }

    private void validarCadastro(UsuarioRequestDTO request) {
        if (!request.getSenha().equals(request.getConfirmarSenha())) {
            throw new RegraDeNegocioException("A confirmação da senha não confere.");
        }
        if (loginJaExiste(request.getLogin())) {
            throw new RegraDeNegocioException("Este login já está em uso.");
        }
        if (emailJaExiste(request.getEmail())) {
            throw new RegraDeNegocioException("Este e-mail já está cadastrado.");
        }
    }

    private Role obterRolePadrao() {
        return roleRepository.findByNomeRole(Role.ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(Role.ROLE_USER)));
    }

    private Endereco converterEndereco(EnderecoRequestDTO endereco) {
        return new Endereco(
                limpar(endereco.getCep()),
                limpar(endereco.getLogradouro()),
                limpar(endereco.getNumero()),
                limparOpcional(endereco.getComplemento()),
                limpar(endereco.getBairro()),
                limpar(endereco.getCidade()),
                limpar(endereco.getUf()).toUpperCase(Locale.ROOT)
        );
    }

    private UsuarioResponseDTO converter(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome() + " " + usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getLogin()
        );
    }

    private String normalizarLogin(String login) {
        return limpar(login).toLowerCase(Locale.ROOT);
    }

    private String normalizarEmail(String email) {
        return limpar(email).toLowerCase(Locale.ROOT);
    }

    private String limpar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private String limparOpcional(String valor) {
        String texto = limpar(valor);
        return texto.isBlank() ? null : texto;
    }
}
