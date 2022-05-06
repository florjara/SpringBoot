package edu.egg.library.controller;

import edu.egg.library.entity.Editorial;
import edu.egg.library.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    
    @Autowired
    private EditorialService editorialService;
    
    @GetMapping
    public ModelAndView obtenerEditoriales(){
        ModelAndView mav = new ModelAndView("tabla-edit-autor");
        mav.addObject("editoriales", editorialService.obtenerEditoriales());
        return mav;
    }
    
        @PostMapping("/crear-editorial")
    public RedirectView crearAutor(Editorial editorialDto) {
        RedirectView redirect = new RedirectView("/editorial");
        editorialService.crearEditorial(editorialDto);
        return redirect;
    }
}
