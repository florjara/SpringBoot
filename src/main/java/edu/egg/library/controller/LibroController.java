package edu.egg.library.controller;

import edu.egg.library.entity.Libro;
import edu.egg.library.service.AutorService;
import edu.egg.library.service.EditorialService;
import edu.egg.library.service.LibroService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
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
    public ModelAndView obtenerLibros(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("tabla");
        mav.addObject("libros", libroService.obtenerLibros());

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
        }

        return mav;
    }

    @GetMapping("/form-crear-libro")
    public ModelAndView formCrear(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-libro");
        mav.addObject("action", "crear-libro");
        mav.addObject("editoriales", editorialService.obtenerEditoriales());
        mav.addObject("autores", autorService.obtenerAutores());

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("libro", inputFlashMap.get("libro"));
        } else {
            mav.addObject("libro", new Libro());
        }

        return mav;
    }

    @GetMapping("/form-actualizar-libro/{id}")
    public ModelAndView formActualizar(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-libro");
        mav.addObject("editoriales", editorialService.obtenerEditoriales());
        mav.addObject("autores", autorService.obtenerAutores());

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("libro", inputFlashMap.get("libro"));
        } else {
            Libro libro = libroService.obtenerLibroPorId(id);
            mav.addObject("libro", libro);
        }

        mav.addObject("action", "actualizar-libro");
        return mav;
    }

    @PostMapping("/crear-libro")
    public RedirectView crearLibro(Libro libroDto, RedirectAttributes attributes) { // este obj libro lo recibo del form
        RedirectView redirect = new RedirectView("/libro");
        try {
            libroService.crearLibro(libroDto);
            attributes.addFlashAttribute("success", "El libro se ha creado correctamente.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("libro", libroDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/libro/form-crear-libro");
        }
        return redirect;
    }

    @PostMapping("/actualizar-libro")
    public RedirectView actualizarLibro(Libro libroDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/libro");
        try {
            libroService.actualizarLibro(libroDto);
            attributes.addFlashAttribute("success", "El libro se ha actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("libro", libroDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            String url = String.format("/libro/form-actualizar-libro/%s", libroDto.getId()); //es necesario?
            redirect.setUrl(url);
        }

        return redirect;
    }

    @GetMapping("/eliminar-libro/{id}")
    public RedirectView eliminarLibro(@PathVariable Long id, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/libro");
        libroService.eliminarLibroPorId(id);
        attributes.addFlashAttribute("success", "Se eliminó joya!");
        return redirect;
    }
}
