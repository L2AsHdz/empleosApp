package com.l2ashdz.empleos.service.db;

import com.l2ashdz.empleos.model.Usuario;
import com.l2ashdz.empleos.repository.UsuarioRepository;
import com.l2ashdz.empleos.service.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceJpa implements IUsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceJpa(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void save(Usuario u) {
        usuarioRepository.save(u);
    }

    @Override
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}
