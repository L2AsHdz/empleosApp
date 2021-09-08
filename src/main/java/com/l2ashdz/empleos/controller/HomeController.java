package com.l2ashdz.empleos.controller;

import com.l2ashdz.empleos.model.Vacante;
import com.l2ashdz.empleos.service.IVacanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private IVacanteService vacanteService;

    public HomeController(IVacanteService vacanteService) {
        this.vacanteService = vacanteService;
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

    @GetMapping("tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> vacantes = vacanteService.findAll();
        model.addAttribute("vacantes", vacantes);
        return "tabla";
    }

}
