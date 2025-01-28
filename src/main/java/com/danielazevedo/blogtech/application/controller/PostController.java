package com.danielazevedo.blogtech.application.controller;

import com.danielazevedo.blogtech.application.dto.PostDTO;
import com.danielazevedo.blogtech.application.dto.response.PostResponseDTO;
import com.danielazevedo.blogtech.application.service.PostService;
import com.danielazevedo.blogtech.application.service.UsuarioService;
import com.danielazevedo.blogtech.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    private final UsuarioService usuarioService;

    @GetMapping("/novo")
    public ModelAndView novoPost() {
        Usuario usuarioAutenticado = usuarioService.obterUsuarioAutenticado();
        ModelAndView modelAndView = new ModelAndView("/novapostagem");
        modelAndView.addObject("autor", usuarioAutenticado); // Passa o objeto Usuario
        modelAndView.addObject("postobj", new PostDTO());
        return modelAndView;
    }


    @PostMapping(value = "/cadastrar", consumes = {"multipart/form-data"})
    public ModelAndView cadastrarPost(@ModelAttribute PostDTO postDTO) throws IOException {
        postService.cadastrarPost(postDTO);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity<ByteArrayResource> renderizarImagem(@PathVariable Long id) {
        return postService.obterImagemDoPost(id);
    }

    @GetMapping("/{id}")
    public ModelAndView visualizarPost(@PathVariable Long id) {
        PostResponseDTO post = postService.buscarPostPorId(id);
        ModelAndView modelAndView = new ModelAndView("/post");
        modelAndView.addObject("postobj", post);
        return modelAndView;
    }
}
