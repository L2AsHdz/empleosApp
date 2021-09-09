package com.l2ashdz.empleos.controller;

import com.l2ashdz.empleos.service.IUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping("/usuarios")
public class UsuariosController {

    private IUsuarioService usuarioService;

    public UsuariosController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/listUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes attributes) {
        usuarioService.delete(id);
        attributes.addFlashAttribute("msg", "Usuario eliminado correctamente");
        return "redirect:/usuarios/index";
    }

}
