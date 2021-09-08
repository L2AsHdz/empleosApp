package com.l2ashdz.empleos.repository;

import com.l2ashdz.empleos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
