package edu.egg.library.controller;

import edu.egg.library.entity.Editorial;
import edu.egg.library.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ModelAndView obtenerEditoriales() {
        ModelAndView mav = new ModelAndView("tabla-editorial");
        mav.addObject("editoriales", editorialService.obtenerEditoriales());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView formCrear() {
        ModelAndView mav = new ModelAndView("form-editorial");
        mav.addObject("editorial", new Editorial());
        mav.addObject("action", "crear-editorial");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView formActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form-editorial");
        mav.addObject("editorial", editorialService.obtenerEditorialPorId(id));
        mav.addObject("action", "actualizar-editorial");
        return mav;
    }

    @PostMapping("/crear-editorial")
    public RedirectView crearEditorial(Editorial editorialDto) {
        RedirectView redirect = new RedirectView("/editorial");
        editorialService.crearEditorial(editorialDto);
        return redirect;
    }
    
    @PostMapping("/actualizar-editorial")
    public RedirectView actualizarEditorial(Editorial editorialDto){
        RedirectView redirectView = new RedirectView("/editorial");
        editorialService.actualizarEditorial(editorialDto);
        return redirectView;
    }
    
    @GetMapping("/eliminar-editorial/{id}")
    public RedirectView eliminarEditorial(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/editorial");
        editorialService.eliminarEditorialPorId(id);
        return redirect;
    }

}
