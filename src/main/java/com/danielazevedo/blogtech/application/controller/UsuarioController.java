package com.danielazevedo.blogtech.application.controller;

import com.danielazevedo.blogtech.application.dto.request.UsuarioRequestDTO;
import com.danielazevedo.blogtech.application.exception.RegraDeNegocioException;
import com.danielazevedo.blogtech.application.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/novo")
    public ModelAndView novoUsuario() {
        return formulario(new UsuarioRequestDTO());
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarUsuario(
            @Valid @ModelAttribute("usuarioobj") UsuarioRequestDTO request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return formulario(request);
        }

        try {
            usuarioService.cadastrarUsuario(request);
        } catch (RegraDeNegocioException exception) {
            bindingResult.reject("cadastro.invalido", exception.getMessage());
            return formulario(request);
        }

        redirectAttributes.addFlashAttribute("usuarioCadastrado", request.getNome().trim());
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/listar")
    public ModelAndView listarUsuarios() {
        return new ModelAndView("listausuarios")
                .addObject("usuarios", usuarioService.listarTodosUsuarios());
    }

    private ModelAndView formulario(UsuarioRequestDTO request) {
        return new ModelAndView("novousuario")
                .addObject("usuarioobj", request);
    }
}
