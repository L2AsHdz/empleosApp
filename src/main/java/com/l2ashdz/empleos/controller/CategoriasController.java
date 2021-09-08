package com.l2ashdz.empleos.controller;

import com.l2ashdz.empleos.model.Categoria;
import com.l2ashdz.empleos.service.ICategoriaService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

    private final ICategoriaService categoriaService;

    public CategoriasController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias/listCategorias";
    }

    @GetMapping("/create")
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute("categoria") Categoria categoria, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> System.out.println(e.getDefaultMessage()));
            return "categorias/formCategoria";
        }
        categoriaService.save(categoria);
        attributes.addFlashAttribute("msg", "Los datos de la categoria fueron guardados correctamente");
        return "redirect:/categorias/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes attributes) {
        categoriaService.delete(id);
        attributes.addFlashAttribute("msg", "Categoria eliminada correctamente!");
        return "redirect:/categorias/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        model.addAttribute("categoria", categoria);
        return "categorias/formCategoria";
    }

}
