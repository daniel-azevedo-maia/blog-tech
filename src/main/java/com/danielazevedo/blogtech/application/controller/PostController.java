package com.danielazevedo.blogtech.application.controller;

import com.danielazevedo.blogtech.application.dto.request.PostRequestDTO;
import com.danielazevedo.blogtech.application.dto.response.ImagemPostDTO;
import com.danielazevedo.blogtech.application.exception.RegraDeNegocioException;
import com.danielazevedo.blogtech.application.service.PostService;
import com.danielazevedo.blogtech.application.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UsuarioService usuarioService;

    public PostController(PostService postService, UsuarioService usuarioService) {
        this.postService = postService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/novo")
    public ModelAndView novoPost(Principal principal) {
        return formulario(new PostRequestDTO(), principal);
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarPost(
            @Valid @ModelAttribute("postobj") PostRequestDTO request,
            BindingResult bindingResult,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return formulario(request, principal);
        }

        try {
            Long postId = postService.cadastrarPost(request, principal.getName());
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Publicação criada com sucesso.");
            return new ModelAndView("redirect:/post/" + postId);
        } catch (RegraDeNegocioException exception) {
            bindingResult.rejectValue("imagemPost", "imagem.invalida", exception.getMessage());
            return formulario(request, principal);
        }
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> renderizarImagem(@PathVariable Long id) {
        ImagemPostDTO imagem = postService.obterImagemDoPost(id);
        ContentDisposition contentDisposition = ContentDisposition.inline()
                .filename(imagem.nomeArquivo(), StandardCharsets.UTF_8)
                .build();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imagem.tipoArquivo()))
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .body(imagem.conteudo());
    }

    @GetMapping("/{id}")
    public ModelAndView visualizarPost(@PathVariable Long id) {
        return new ModelAndView("post")
                .addObject("postobj", postService.buscarPostPorId(id));
    }

    private ModelAndView formulario(PostRequestDTO request, Principal principal) {
        String autor = usuarioService.buscarNomePorLogin(principal.getName());
        return new ModelAndView("novapostagem")
                .addObject("postobj", request)
                .addObject("autor", autor);
    }
}
