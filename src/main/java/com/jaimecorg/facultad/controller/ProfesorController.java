package com.jaimecorg.facultad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jaimecorg.facultad.services.ProfesorService;

import model.Profesor;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {
    
    @Autowired
    ProfesorService profesorService;

    @GetMapping(value = "/list")
    public ModelAndView listPage(Model model) {

        List<Profesor> profesores = profesorService.findAll();

        ModelAndView modelAndView = new ModelAndView("profesores/list");
        modelAndView.addObject("profesores", profesores);
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) String codigo) {

        Profesor profesor = profesorService.findById(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profesor", profesor);
        modelAndView.setViewName("profesores/edit");
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Profesor profesor) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profesor", new Profesor());
        modelAndView.setViewName("profesores/new");
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Profesor profesor) {

        Profesor save = profesorService.insert(profesor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + save.getId());
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Profesor profesor) {

        profesorService.update(profesor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + profesor.getId());
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) String id) {

        profesorService.delete(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../list");
        return modelAndView;
    }
}
