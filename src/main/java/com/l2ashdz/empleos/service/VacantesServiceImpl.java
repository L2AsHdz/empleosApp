package com.l2ashdz.empleos.service;

import com.l2ashdz.empleos.model.Vacante;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class VacantesServiceImpl implements IVacanteService {

    private List<Vacante> vacantes = null;

    public VacantesServiceImpl() {
        vacantes = new LinkedList();

        vacantes.add(Vacante.builder()
                .id(1)
                .nombre("Ingeniero civil")
                .descripcion("Solicitamos Ing. Civil para diseñar puente peatonal.")
                .fecha(LocalDate.parse("2019-02-08"))
                .salario(14000d)
                .destacado(1)
                .estatus("Aprobada")
                .imagen("empresa1.png")
                .build());

        vacantes.add(Vacante.builder()
                .id(2)
                .nombre("Contador publico")
                .descripcion("Empresa importante solicita contador con 5 años de experiencia titulado.")
                .fecha(LocalDate.parse("2019-02-09"))
                .salario(12000d)
                .destacado(0)
                .imagen("empresa2.png")
                .estatus("Creada")
                .build());

        vacantes.add(Vacante.builder()
                .id(3)
                .nombre("Ingeniero Electrico")
                .descripcion("Empresa internacional solicita Ingeniero mecanico para mantenimiento de la instalacion electrica")
                .fecha(LocalDate.parse("2019-02-10"))
                .salario(10500d)
                .destacado(0)
                .estatus("Aprobada")
                .imagen("no-image.png")
                .build());

        vacantes.add(Vacante.builder()
                .id(4)
                .nombre("Diseñador Grafico")
                .descripcion("Solicitamos diseñádor grafico titulado para diseñar publicidad de la empresa.")
                .fecha(LocalDate.parse("2019-02-11"))
                .salario(7500d)
                .destacado(1)
                .estatus("Eliminada")
                .imagen("empresa3.png")
                .build());
    }

    @Override
    public List<Vacante> findAll() {
        return vacantes;
    }

    @Override
    public Vacante findById(int id) {
        for (Vacante v : vacantes) {
            if (v.getId() == id) return v;
        }
        return null;
    }

    @Override
    public void save(Vacante v) {
        vacantes.add(v);
    }

    @Override
    public List<Vacante> findDestacados() {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public List<Vacante> findNoEliminados() {
        return null;
    }


}
