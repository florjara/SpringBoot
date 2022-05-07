package edu.egg.library.controller;

import edu.egg.library.entity.Autor;
import edu.egg.library.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ModelAndView obtenerEditoriales() {
        ModelAndView mav = new ModelAndView("tabla-autor");
        mav.addObject("autores", autorService.obtenerAutores());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView formCrear() {
        ModelAndView mav = new ModelAndView("form-autor");
        mav.addObject("autor", new Autor());
        mav.addObject("action", "crear-autor");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView formActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form-autor");
        mav.addObject("autor", autorService.obtenerAutorPorId(id));
        mav.addObject("action", "actualizar-autor");
        return mav;
    }

    @PostMapping("/crear-autor")
    public RedirectView crearAutor(Autor autorDto) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.crearAutor(autorDto);
        return redirect;
    }

    @PostMapping("/actualizar-autor")
    public RedirectView actualizarAutor(Autor autorDto) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.actualizarAutor(autorDto);
        return redirect;
    }

    @GetMapping("/eliminar-autor/{id}")
    public RedirectView eliminarAutor(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.eliminarAutorPorId(id);
        return redirect;
    }
}
