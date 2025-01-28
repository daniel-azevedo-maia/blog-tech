package com.danielazevedo.blogtech.application.controller;

import com.danielazevedo.blogtech.application.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.application.dto.response.UsuarioResponseDTO;
import com.danielazevedo.blogtech.application.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/novo")
    public ModelAndView novoUsuario() {
        ModelAndView modelAndView = new ModelAndView("/novousuario");
        modelAndView.addObject("usuarioobj", new UsuarioRequestDTO());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarUsuario(@ModelAttribute UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioService.verificarExistenciaLogin(usuarioRequestDTO.getLogin())) {
            return retornarErroCadastroUsuario("Este login já existe! Informe outro.");
        }

        usuarioService.cadastrarUsuario(usuarioRequestDTO);
        return new ModelAndView("login").addObject("usuarioCadastrado", usuarioRequestDTO.getNome());
    }

    @GetMapping("/listar")
    public ModelAndView listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodosUsuarios();
        return new ModelAndView("listausuarios").addObject("usuarios", usuarios);
    }

    private ModelAndView retornarErroCadastroUsuario(String mensagemErro) {
        ModelAndView modelAndView = new ModelAndView("/novousuario");
        modelAndView.addObject("usuarioobj", new UsuarioRequestDTO());
        modelAndView.addObject("loginExistente", mensagemErro);
        return modelAndView;
    }
}
