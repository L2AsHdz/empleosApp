package com.l2ashdz.empleos.controller;

import com.l2ashdz.empleos.model.Perfil;
import com.l2ashdz.empleos.model.Usuario;
import com.l2ashdz.empleos.model.Vacante;
import com.l2ashdz.empleos.service.IUsuarioService;
import com.l2ashdz.empleos.service.IVacanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private IVacanteService vacanteService;
    private IUsuarioService usuarioService;

    public HomeController(IVacanteService vacanteService, IUsuarioService usuarioService) {
        this.vacanteService = vacanteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/", "", "home"})
    public String goHome(Model model) {
        List<Vacante> vacantes = vacanteService.findDestacados();
        model.addAttribute("vacantes", vacantes);

        return "home";
    }

    @GetMapping("/listado")
    public String goList(Model model) {
        List<String> list = new ArrayList();
        list.add("Ingeniero en sistemas");
        list.add("Vendedor");
        list.add("Cajero");
        list.add("Consultor");
        model.addAttribute("list", list);
        return "listado";
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        Vacante vacante = Vacante.builder()
                .nombre("Ingeniero de comunicaciones")
                .descripcion("Se solicita ingeniero para dar soporte a intranet")
                .fecha(LocalDate.now())
                .salario(9700d)
                .build();

        model.addAttribute("vacante", vacante);

        return "detalle";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> vacantes = vacanteService.findAll();
        model.addAttribute("vacantes", vacantes);
        return "tabla";
    }

    @GetMapping("/singup")
    public String registrarse() {
        return "formRegistro";
    }

    @PostMapping("/singup")
    public String saveRegistro(@ModelAttribute Usuario usuario, RedirectAttributes attributes) {
        usuario.addPerfil(Perfil.builder().id(3).build());
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setEstatus(1);
        usuarioService.save(usuario);
        attributes.addFlashAttribute("msg", "El usuario se registro correctamente");
        return "redirect:/usuarios/index";
    }

}
