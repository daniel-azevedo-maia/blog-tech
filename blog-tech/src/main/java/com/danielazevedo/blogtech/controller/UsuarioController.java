package com.danielazevedo.blogtech.controller;

import com.danielazevedo.blogtech.dto.UsuarioDTO;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import com.danielazevedo.blogtech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    // Cadastrar novo usuário:
    @GetMapping("/novo")
    public ModelAndView novousuario() {
        ModelAndView modelAndView = new ModelAndView("/novousuario");
        modelAndView.addObject("usuarioobj", new UsuarioDTO());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarUsuario(@ModelAttribute UsuarioDTO usuarioDTO) {
        usuarioService.cadastrarUsuario(usuarioDTO);
        ModelAndView modelAndView = new ModelAndView("/novousuario");
        modelAndView.addObject("usuarioobj", new UsuarioDTO());
        return modelAndView;
    }

}
