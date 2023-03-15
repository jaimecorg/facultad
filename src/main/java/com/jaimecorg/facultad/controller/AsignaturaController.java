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

import com.jaimecorg.facultad.services.AsignaturaService;

import model.Asignatura;

@Controller
@RequestMapping("/asignaturas")
public class AsignaturaController {
    @Autowired
    AsignaturaService asignaturaService;

    @GetMapping(value = "/list")
    public ModelAndView listPage(Model model) {

        List<Asignatura> asignaturas = asignaturaService.findAll();

        ModelAndView modelAndView = new ModelAndView("asignaturas/list");
        modelAndView.addObject("asignaturas", asignaturas);
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Asignatura asignatura = asignaturaService.find(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("asignatura", asignatura);
        modelAndView.setViewName("asignaturas/edit");
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Asignatura asignatura) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("asignatura", new Asignatura());
        modelAndView.setViewName("asignaturas/new");
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Asignatura asignatura) {

        Asignatura save = asignaturaService.save(asignatura);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + save.getCodigo());
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Asignatura asignatura) {

        asignaturaService.update(asignatura);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + asignatura.getCodigo());
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int id) {

        asignaturaService.delete(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../list");
        return modelAndView;
    }
}
