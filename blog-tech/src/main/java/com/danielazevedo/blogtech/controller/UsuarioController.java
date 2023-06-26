package com.danielazevedo.blogtech.controller;


import com.danielazevedo.blogtech.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.dto.response.UsuarioResponseDTO;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import com.danielazevedo.blogtech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Cadastrar novo usuário:
    @GetMapping("/novo")
    public ModelAndView novousuario() {
        ModelAndView modelAndView = new ModelAndView("/novousuario");
        modelAndView.addObject("usuarioobj", new UsuarioRequestDTO());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarUsuario(@ModelAttribute UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioService.cadastrarUsuario(usuarioRequestDTO);
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("usuarioCadastrado", usuario.getNome());
//        System.out.println("Cadastrada a pessoa " + usuario.getNome());
        return modelAndView;
    }

    // Listar usuários:
    @GetMapping("/listar")
    public ModelAndView listarTodos() {

        List<UsuarioResponseDTO> usuariosEncontrados = usuarioService.listarTodos();
        ModelAndView modelAndView = new ModelAndView("listausuarios");
        modelAndView.addObject("usuarios", usuariosEncontrados);

        return modelAndView;
    }



}
