package com.l2ashdz.empleos.service;

import com.l2ashdz.empleos.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    void save(Usuario u);
    void delete(int id);
    List<Usuario> findAll();
    Usuario findByUsername(String username);
}
