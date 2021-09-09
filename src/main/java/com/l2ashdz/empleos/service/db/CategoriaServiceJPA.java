package com.l2ashdz.empleos.service.db;

import com.l2ashdz.empleos.model.Categoria;
import com.l2ashdz.empleos.repository.CategoriaRepository;
import com.l2ashdz.empleos.service.ICategoriaService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoriaServiceJPA implements ICategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaServiceJPA(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(int id) {
        Optional<Categoria> oCategoria = categoriaRepository.findById(id);

        if (oCategoria.isPresent()) return oCategoria.get();
        return null;
    }

    @Override
    public void delete(int id) {
        categoriaRepository.delete(Categoria.builder().id(id).build());
    }

    @Override
    public Page<Categoria> findAll(Pageable page) {
        return categoriaRepository.findAll(page);
    }

}
