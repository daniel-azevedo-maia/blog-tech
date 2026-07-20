package com.danielazevedo.blogtech.application.service;

import com.danielazevedo.blogtech.application.dto.request.PostRequestDTO;
import com.danielazevedo.blogtech.application.dto.response.ImagemPostDTO;
import com.danielazevedo.blogtech.application.dto.response.PostResponseDTO;
import com.danielazevedo.blogtech.application.dto.response.PostResumoDTO;
import com.danielazevedo.blogtech.application.exception.RecursoNaoEncontradoException;
import com.danielazevedo.blogtech.application.exception.RegraDeNegocioException;
import com.danielazevedo.blogtech.domain.model.Post;
import com.danielazevedo.blogtech.domain.model.Usuario;
import com.danielazevedo.blogtech.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class PostService {

    private static final Set<String> TIPOS_DE_IMAGEM_PERMITIDOS = Set.of(
            "image/jpeg", "image/png", "image/webp", "image/gif"
    );

    private final PostRepository postRepository;
    private final UsuarioService usuarioService;
    private final Clock clock;

    public PostService(PostRepository postRepository, UsuarioService usuarioService, Clock clock) {
        this.postRepository = postRepository;
        this.usuarioService = usuarioService;
        this.clock = clock;
    }

    @Transactional
    public Long cadastrarPost(PostRequestDTO request, String loginAutor) {
        Usuario autor = usuarioService.buscarPorLogin(loginAutor);
        Post post = new Post(
                autor,
                request.getTitulo().trim(),
                request.getSubtitulo().trim(),
                request.getCategoria().trim(),
                request.getTextoPrincipal().trim(),
                LocalDateTime.now(clock)
        );

        adicionarImagemSeInformada(post, request.getImagemPost());
        return postRepository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public PostResponseDTO buscarPostPorId(Long id) {
        Post post = buscarEntidade(id);
        return new PostResponseDTO(
                post.getId(),
                nomeCompleto(post.getUsuario()),
                post.getTitulo(),
                post.getSubtitulo(),
                post.getCategoria(),
                post.getTextoPrincipal(),
                post.possuiImagem(),
                post.getDataPublicacao()
        );
    }

    @Transactional(readOnly = true)
    public List<PostResumoDTO> listarTodosPosts() {
        return postRepository.findAllByOrderByDataPublicacaoDesc()
                .stream()
                .map(post -> new PostResumoDTO(
                        post.getId(),
                        nomeCompleto(post.getUsuario()),
                        post.getTitulo(),
                        post.getSubtitulo(),
                        post.getCategoria(),
                        post.possuiImagem(),
                        post.getDataPublicacao()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public ImagemPostDTO obterImagemDoPost(Long id) {
        Post post = buscarEntidade(id);
        if (!post.possuiImagem()) {
            throw new RecursoNaoEncontradoException("Este post não possui imagem.");
        }
        return new ImagemPostDTO(
                post.getImagemPost(),
                post.getTipoFile(),
                post.getNomeFile()
        );
    }

    private Post buscarEntidade(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado."));
    }

    private void adicionarImagemSeInformada(Post post, MultipartFile imagem) {
        if (imagem == null || imagem.isEmpty()) {
            return;
        }

        String tipoArquivo = imagem.getContentType();
        if (tipoArquivo == null || !TIPOS_DE_IMAGEM_PERMITIDOS.contains(tipoArquivo.toLowerCase(Locale.ROOT))) {
            throw new RegraDeNegocioException("A imagem deve estar nos formatos JPG, PNG, WEBP ou GIF.");
        }

        try {
            String caminhoLimpo = StringUtils.cleanPath(
                    imagem.getOriginalFilename() == null ? "imagem" : imagem.getOriginalFilename()
            );
            String nomeArquivo = StringUtils.getFilename(caminhoLimpo);
            if (!StringUtils.hasText(nomeArquivo)) {
                nomeArquivo = "imagem";
            }
            post.definirImagem(imagem.getBytes(), nomeArquivo, tipoArquivo);
        } catch (IOException exception) {
            throw new RegraDeNegocioException("Não foi possível ler a imagem enviada.");
        }
    }

    private String nomeCompleto(Usuario usuario) {
        return usuario.getNome() + " " + usuario.getSobrenome();
    }
}
