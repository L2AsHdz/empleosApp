package com.l2ashdz.empleos.service.db;

import com.l2ashdz.empleos.model.Vacante;
import com.l2ashdz.empleos.repository.VacanteRepository;
import com.l2ashdz.empleos.service.IVacanteService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VacanteServiceJpa implements IVacanteService {

    private VacanteRepository vacanteRepository;

    public VacanteServiceJpa(VacanteRepository vacanteRepository) {
        this.vacanteRepository = vacanteRepository;
    }

    @Override
    public List<Vacante> findAll() {
        return vacanteRepository.findAll();
    }

    @Override
    public Vacante findById(int id) {
        Optional<Vacante> oVacante = vacanteRepository.findById(id);

        if (oVacante.isPresent()) return oVacante.get();
        return null;
    }

    @Override
    public void save(Vacante v) {
        vacanteRepository.save(v);
    }

    @Override
    public List<Vacante> findDestacados() {
        return vacanteRepository.findByDestacadoAndEstatusOrderByIdDesc(1,"Aprobada");
    }

    @Override
    public void eliminar(int id) {
        Vacante vacante = vacanteRepository.findById(id).get();
        vacante.setEstatus("Eliminada");
        vacanteRepository.save(vacante);
    }

    @Override
    public List<Vacante> findNoEliminados() {
        return vacanteRepository.findByEstatusIn(new String[] {"Aprobada", "Creada"});
    }

}
