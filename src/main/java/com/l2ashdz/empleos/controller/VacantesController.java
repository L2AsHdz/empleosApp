package com.l2ashdz.empleos.controller;

import static com.l2ashdz.utils.FileController.saveMultipartFileSpring;

import com.l2ashdz.empleos.model.Vacante;
import com.l2ashdz.empleos.service.ICategoriaService;
import com.l2ashdz.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    private final IVacanteService vacanteService;
    private final ICategoriaService categoriaService;

    @Value("${empleos.ruta.imagenes}")
    private String ruta;

    public VacantesController(IVacanteService vacanteService, ICategoriaService categoriaService) {
        this.vacanteService = vacanteService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Vacante vacante = vacanteService.findById(id);
        model.addAttribute("vacante", vacante);
        model.addAttribute("categorias", categoriaService.findAll());
        return "vacantes/formVacante";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = vacanteService.findById(idVacante);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @PostMapping("save")
    public String save(@Valid @ModelAttribute Vacante vacante,
                       BindingResult result,
                       RedirectAttributes attributes,
                       Model model,
                       @RequestParam("archivoImagen")MultipartFile multipartFile) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.findAll());
            return "vacantes/formVacante";
        }

        if (!multipartFile.isEmpty()) {
            String imageName = saveMultipartFileSpring(multipartFile, ruta);
            if (imageName != null) vacante.setImagen(imageName);
        }

        System.out.println(vacante.toString());
        vacanteService.save(vacante);
        attributes.addFlashAttribute("msg", "Registro guardado!");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes) {
        vacanteService.eliminar(idVacante);
        attributes.addFlashAttribute("msg", "Registro eliminado exitosamente");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "vacantes/formVacante";
    }

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("vacantes", vacanteService.findNoEliminados());
        return "vacantes/listVacantes";
    }

    @GetMapping("/indexPaginate")
    public String indexPaginate(Model model, Pageable page) {
        Page<Vacante> vacantes = vacanteService.findAll(page);
        model.addAttribute("vacantes", vacantes);
        return "vacantes/listVacantes";
    }

    /*
    * Agrega atributos a todos los metodos
    @ModelAttribute
    public void setAttributes(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
    }*/

}
