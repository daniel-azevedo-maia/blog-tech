package com.danielazevedo.blogtech.application.service;

import com.danielazevedo.blogtech.application.dto.request.EnderecoRequestDTO;
import com.danielazevedo.blogtech.application.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.domain.model.Role;
import com.danielazevedo.blogtech.domain.model.Usuario;
import com.danielazevedo.blogtech.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UsuarioServiceIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void deveCadastrarUsuarioComEnderecoSenhaCriptografadaERolePadrao() {
        UsuarioRequestDTO request = criarUsuario("daniel-teste", "daniel.teste@example.com");

        usuarioService.cadastrarUsuario(request);

        Usuario usuario = usuarioRepository.findByLoginIgnoreCase("daniel-teste").orElseThrow();
        assertThat(usuario.getEndereco().getCidade()).isEqualTo("João Pessoa");
        assertThat(passwordEncoder.matches("senha123", usuario.getSenha())).isTrue();
        assertThat(usuario.getRoles()).extracting(Role::getAuthority).contains(Role.ROLE_USER);
    }

    static UsuarioRequestDTO criarUsuario(String login, String email) {
        EnderecoRequestDTO endereco = new EnderecoRequestDTO();
        endereco.setCep("58000-000");
        endereco.setLogradouro("Rua Teste");
        endereco.setNumero("100");
        endereco.setBairro("Centro");
        endereco.setCidade("João Pessoa");
        endereco.setUf("PB");

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Daniel");
        request.setSobrenome("Teste");
        request.setEmail(email);
        request.setLogin(login);
        request.setSenha("senha123");
        request.setConfirmarSenha("senha123");
        request.setEndereco(endereco);
        return request;
    }
}
