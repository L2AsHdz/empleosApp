package com.l2ashdz.empleos.service;

import com.l2ashdz.empleos.model.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVacanteService {

    List<Vacante> findAll();
    Vacante findById(int id);
    void save(Vacante v);
    List<Vacante> findDestacados();
    void eliminar(int id);
    List<Vacante> findNoEliminados();
    List<Vacante> findByExample(Example<Vacante> example);
    Page<Vacante> findAll(Pageable page);
}
