package edu.egg.library.controller;

import edu.egg.library.entity.Libro;
import edu.egg.library.service.AutorService;
import edu.egg.library.service.EditorialService;
import edu.egg.library.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private EditorialService editorialService;
    @Autowired
    private AutorService autorService;

    @GetMapping()
    public ModelAndView obtenerLibros() {
        ModelAndView mav = new ModelAndView("tabla");
        mav.addObject("libros", libroService.obtenerLibros());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView obtenerFormulario() {
        ModelAndView mav = new ModelAndView("form-libro");
        Libro libro = new Libro();
        mav.addObject("libro", libro);
        mav.addObject("editoriales", editorialService.obtenerEditoriales());
        mav.addObject("autores", autorService.obtenerAutores());
        return mav;
    }

    @PostMapping("/crear-libro")
    public RedirectView crearLibro(Libro libroDto) { // este obj libro lo recibo del form
        RedirectView redirect = new RedirectView("/libro");
        libroService.crearLibro(libroDto);
        return redirect;
    }
}
