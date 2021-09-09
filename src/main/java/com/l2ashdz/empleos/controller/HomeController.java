package com.l2ashdz.empleos.controller;

import com.l2ashdz.empleos.model.Perfil;
import com.l2ashdz.empleos.model.Usuario;
import com.l2ashdz.empleos.model.Vacante;
import com.l2ashdz.empleos.service.ICategoriaService;
import com.l2ashdz.empleos.service.IUsuarioService;
import com.l2ashdz.empleos.service.IVacanteService;
import org.dom4j.rule.Mode;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
    private ICategoriaService categoriaService;

    public HomeController(IVacanteService vacanteService, IUsuarioService usuarioService, ICategoriaService categoriaService) {
        this.vacanteService = vacanteService;
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/", "", "home"})
    public String goHome(Model model) {
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

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Vacante> vacanteExample = Example.of(vacante, matcher);
        List<Vacante> vacantes = vacanteService.findByExample(vacanteExample);
        model.addAttribute("vacantes", vacantes);
        return "home";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public void setAttributes(Model model) {
        Vacante vacante = Vacante.builder().build();
        vacante.reset();
        model.addAttribute("search", vacante);
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("vacantes", vacanteService.findDestacados());
    }
}
