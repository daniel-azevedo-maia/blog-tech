package com.danielazevedo.blogtech.controller;

import com.danielazevedo.blogtech.controller.dto.UsuarioDTO;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import com.danielazevedo.blogtech.service.UsuarioService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    // Cadastrar novo usuário:
    @GetMapping("/usuario/novo")
    public ModelAndView novousuario() {
        ModelAndView modelAndView = new ModelAndView("/novousuario");
        modelAndView.addObject("usuarioobj", new UsuarioDTO());
        return modelAndView;
    }

    @PostMapping("/usuario/cadastrar")
    public ModelAndView cadastrarUsuario(UsuarioDTO usuarioDTO) {
        usuarioService.cadastrarUsuario(usuarioDTO);
        ModelAndView modelAndView = new ModelAndView("/novousuario");
        modelAndView.addObject("usuarioobj", new UsuarioDTO());
        return modelAndView;
    }

}
