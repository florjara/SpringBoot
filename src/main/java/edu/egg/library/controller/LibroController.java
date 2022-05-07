package edu.egg.library.controller;

import edu.egg.library.entity.Libro;
import edu.egg.library.service.AutorService;
import edu.egg.library.service.EditorialService;
import edu.egg.library.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/form-crear-libro")
    public ModelAndView formCrear() {
        ModelAndView mav = new ModelAndView("form-libro");
        Libro libro = new Libro();
        mav.addObject("libro", libro);
        mav.addObject("editoriales", editorialService.obtenerEditoriales());
        mav.addObject("autores", autorService.obtenerAutores());
        mav.addObject("action", "crear-libro");
        return mav;
    }

    @GetMapping("/form-actualizar-libro/{id}")
    public ModelAndView formActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form-libro");
        Libro libro = libroService.obtenerLibroPorId(id);
        mav.addObject("libro", libro);
        mav.addObject("editoriales", editorialService.obtenerEditoriales());
        mav.addObject("autores", autorService.obtenerAutores());
        mav.addObject("action", "actualizar-libro");
        return mav;
    }

    @PostMapping("/crear-libro")
    public RedirectView crearLibro(Libro libroDto) { // este obj libro lo recibo del form
        RedirectView redirect = new RedirectView("/libro");
        libroService.crearLibro(libroDto);
        return redirect;
    }

    @PostMapping("/actualizar-libro")
    public RedirectView actualizarLibro(Libro libroDto) {
        RedirectView redirect = new RedirectView("/libro");
        libroService.actualizarLibro(libroDto);
        return redirect;
    }

    @GetMapping("/eliminar-libro/{id}")
    public RedirectView eliminarLibro(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/libro");
        libroService.eliminarLibroPorId(id);
        return redirect;
    }
}