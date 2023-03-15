package com.jaimecorg.facultad.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jaimecorg.facultad.repository.AsignaturaRepository;
import com.jaimecorg.facultad.services.AsignaturaService;

import model.Asignatura;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public List<Asignatura> findAll() {
        return asignaturaRepository.findAll();

    }

    @Override
    public Asignatura find(int codigo) {
        Optional<Asignatura> findById = asignaturaRepository.findById(codigo);
        if (findById != null) {
            return findById.get();
        }
        return null;
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        asignaturaRepository.save(asignatura);
        return asignatura;
    }

    @Override
    public void update(Asignatura asignatura) {
        asignaturaRepository.save(asignatura);
    }

    @Override
    public void delete(int codigo) {
        asignaturaRepository.deleteById(codigo);
    }
    
}
