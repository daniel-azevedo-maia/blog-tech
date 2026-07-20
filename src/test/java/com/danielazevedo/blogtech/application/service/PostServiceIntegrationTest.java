package com.danielazevedo.blogtech.application.service;

import com.danielazevedo.blogtech.application.dto.request.PostRequestDTO;
import com.danielazevedo.blogtech.application.dto.response.ImagemPostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.danielazevedo.blogtech.application.service.UsuarioServiceIntegrationTest.criarUsuario;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PostServiceIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostService postService;

    @Test
    void deveCriarListarERecuperarImagemDoPost() {
        usuarioService.cadastrarUsuario(criarUsuario("autor-post", "autor.post@example.com"));

        PostRequestDTO request = new PostRequestDTO();
        request.setTitulo("Spring Boot na prática");
        request.setSubtitulo("Uma aplicação web completa");
        request.setCategoria("Programação e Desenvolvimento");
        request.setTextoPrincipal("Conteúdo do artigo.");
        request.setImagemPost(new MockMultipartFile(
                "imagemPost", "capa.png", "image/png", new byte[]{1, 2, 3}
        ));

        Long id = postService.cadastrarPost(request, "autor-post");
        ImagemPostDTO imagem = postService.obterImagemDoPost(id);

        assertThat(postService.listarTodosPosts()).hasSize(1);
        assertThat(postService.buscarPostPorId(id).nomeAutor()).isEqualTo("Daniel Teste");
        assertThat(imagem.tipoArquivo()).isEqualTo("image/png");
        assertThat(imagem.conteudo()).containsExactly((byte) 1, (byte) 2, (byte) 3);
    }
}
