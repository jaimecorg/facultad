package com.jaimecorg.facultad.services.impl;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


import com.jaimecorg.facultad.repository.ProfesorRepository;
import com.jaimecorg.facultad.services.ProfesorService;

import model.Profesor;

public class ProfesorServiceImpl implements ProfesorService{

    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> findAll() {
        return profesorRepository.findAll();

    }

    @Override
    public Profesor findById(String id) {
        Optional<Profesor> findById = profesorRepository.findById(id);
        if (findById != null) {
            return findById.get();
        }
        return null;
    }

    @Override
    public Profesor insert(Profesor profesor) {
        profesorRepository.save(profesor);
        return profesor;

    }

    @Override
    public void update(Profesor profesor) {
        profesorRepository.save(profesor);

    }

    @Override
    public void delete(String id) {
        profesorRepository.deleteById(id);
    }

    
}
