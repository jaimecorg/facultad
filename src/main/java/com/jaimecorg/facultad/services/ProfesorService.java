package com.jaimecorg.facultad.services;

import java.util.List;

import model.Profesor;

public interface ProfesorService {
    public List<Profesor> findAll();
    public Profesor findById(String id);
    public Profesor insert(Profesor profesor);
    public void update(Profesor profesor);
    public void delete(String id);
}
