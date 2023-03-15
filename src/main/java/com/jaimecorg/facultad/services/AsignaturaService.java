package com.jaimecorg.facultad.services;

import java.util.List;

import model.Asignatura;

public interface AsignaturaService {
    public List<Asignatura> findAll();
    public Asignatura find(int codigo);
    public Asignatura save(Asignatura asignatura);
    public void update(Asignatura asignatura);
    public void delete(int codigo);
}
