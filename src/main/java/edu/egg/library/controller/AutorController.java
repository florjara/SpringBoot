package edu.egg.library.controller;

import edu.egg.library.entity.Autor;
import edu.egg.library.service.AutorService;
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
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ModelAndView obtenerEditoriales(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("tabla-autor");
        mav.addObject("autores", autorService.obtenerAutores());

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
        }
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView formCrear(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-autor");

        mav.addObject("action", "crear-autor");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("autor", inputFlashMap.get("autor"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("autor", new Autor());
        }

        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView formActualizar(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-autor");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("autor", inputFlashMap.get("autor"));
        } else {
            mav.addObject("autor", autorService.obtenerAutorPorId(id));
        }
        mav.addObject("action", "actualizar-autor");
        return mav;
    }

    @PostMapping("/crear-autor")
    public RedirectView crearAutor(Autor autorDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/autor");
        try {
            autorService.crearAutor(autorDto);
            attributes.addFlashAttribute("success", "Se ha creado correctamente.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("exception", e.getMessage());
            attributes.addFlashAttribute("autor", autorDto);
            redirect.setUrl("/autor/form");
        }
        return redirect;
    }

    @PostMapping("/actualizar-autor")
    public RedirectView actualizarAutor(Autor autorDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/autor");
        try {
            autorService.actualizarAutor(autorDto);
            attributes.addFlashAttribute("success", "Se ha actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("exception", e.getMessage());
            attributes.addFlashAttribute("autor", autorDto);
            String url = String.format("/autor/form/%s", autorDto.getId());
            redirect.setUrl(url);
        }
        return redirect;
    }

    @GetMapping("/eliminar-autor/{id}")
    public RedirectView eliminarAutor(@PathVariable Long id, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.eliminarAutorPorId(id);
        attributes.addFlashAttribute("success", "Se ha eliminado correctamente.");
        return redirect;
    }
}
