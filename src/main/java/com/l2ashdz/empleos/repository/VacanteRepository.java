package com.l2ashdz.empleos.repository;

import com.l2ashdz.empleos.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

    List<Vacante> findByEstatus(String estatus);
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(Integer destacado, String estatus);
    List<Vacante> findBySalarioBetweenOrderBySalarioDesc(Double salario1, Double salario2);
    List<Vacante> findByEstatusIn(String[] estatus);
}