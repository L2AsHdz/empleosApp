package com.l2ashdz.empleos.service;

import com.l2ashdz.empleos.model.Categoria;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Qualifier("NoDB")
public class CategoriaServiceImpl implements ICategoriaService {

    private List<Categoria> categorias = null;

    public CategoriaServiceImpl() {
        categorias = new LinkedList();

        categorias.add(Categoria.builder()
                .id(1)
                .nombre("Ventas")
                .descripcion("Descripcion ventas")
                .build());

        categorias.add(Categoria.builder()
                .id(2)
                .nombre("Informatica")
                .descripcion("Descripcion informatica")
                .build());

        categorias.add(Categoria.builder()
                .id(3)
                .nombre("Recursos Humanos")
                .descripcion("Descripcion recursos humanos")
                .build());

        categorias.add(Categoria.builder()
                .id(4)
                .nombre("Contabilidad")
                .descripcion("Descripcion contabilidad")
                .build());

        categorias.add(Categoria.builder()
                .id(5)
                .nombre("Comunicaciones")
                .descripcion("Descripcion comunicaciones")
                .build());

        categorias.add(Categoria.builder()
                .id(6)
                .nombre("Arquitectura")
                .descripcion("Descripcion arquitectura")
                .build());

        categorias.add(Categoria.builder()
                .id(7)
                .nombre("Educacion")
                .descripcion("Descripcion educacion")
                .build());
    }

    @Override
    public void save(Categoria categoria) {
        categoria.setId(categorias.size() + 1);
        categorias.add(categoria);
    }

    @Override
    public List<Categoria> findAll() {
        return categorias;
    }

    @Override
    public Categoria findById(int id) {

        for (Categoria c: categorias) {
            if (c.getId() == id) return c;
        }

        return null;
    }

    @Override
    public void delete(int id) {

    }
}
