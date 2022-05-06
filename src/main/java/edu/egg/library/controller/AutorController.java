package edu.egg.library.controller;

import edu.egg.library.entity.Autor;
import edu.egg.library.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        ModelAndView mav = new ModelAndView("tabla-edit-autor");
        mav.addObject("editoriales", autorService.obtenerAutores());
        return mav; //corregir
    }

    @GetMapping("/form")
    public ModelAndView obtenerFormulario() {
        ModelAndView mav = new ModelAndView("form-edit-autor");
        mav.addObject("autor", new Autor());
        return mav;
    }

    @PostMapping("/crear-autor")
    public RedirectView crearAutor(Autor autorDto) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.crearAutor(autorDto);
        return redirect;
    }
    

}
