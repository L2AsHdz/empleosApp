package com.l2ashdz.empleos.service;

import com.l2ashdz.empleos.model.Categoria;

import java.util.List;

public interface ICategoriaService {

    void save(Categoria categoria);
    List<Categoria> findAll();
    Categoria findById(int id);
    void delete(int id);
}
