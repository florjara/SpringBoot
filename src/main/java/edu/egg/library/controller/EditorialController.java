package edu.egg.library.controller;

import edu.egg.library.entity.Editorial;
import edu.egg.library.service.EditorialService;
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
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public ModelAndView obtenerEditoriales(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("tabla-editorial");
        mav.addObject("editoriales", editorialService.obtenerEditoriales());

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
        }
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView formCrear(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-editorial");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("editorial", inputFlashMap.get("editorial"));
        } else {
            mav.addObject("editorial", new Editorial());
        }

        mav.addObject("action", "crear-editorial");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView formActualizar(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-editorial");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("editorial", inputFlashMap.get("editorial"));
        } else {
            mav.addObject("editorial", editorialService.obtenerEditorialPorId(id));
        }

        mav.addObject("action", "actualizar-editorial");
        return mav;
    }

    @PostMapping("/crear-editorial")
    public RedirectView crearEditorial(Editorial editorialDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/editorial");
        try {
            editorialService.crearEditorial(editorialDto);
            attributes.addFlashAttribute("success", "Se ha creado la editorial.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("exception", e.getMessage());
            attributes.addFlashAttribute("editorial", editorialDto);
            redirect.setUrl("/editorial/form");
        }
        return redirect;
    }

    @PostMapping("/actualizar-editorial")
    public RedirectView actualizarEditorial(Editorial editorialDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/editorial");

        try {
            editorialService.actualizarEditorial(editorialDto);
            attributes.addFlashAttribute("success", "Se ha actualizado la editorial.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("exception", e.getMessage());
            attributes.addFlashAttribute("editorial", editorialDto);
            String url = String.format("/editorial/form/%s", editorialDto.getId());
            redirect.setUrl(url);
        }
        return redirect;
    }

    @GetMapping("/eliminar-editorial/{id}")
    public RedirectView eliminarEditorial(@PathVariable Long id, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/editorial");
        editorialService.eliminarEditorialPorId(id);
        attributes.addFlashAttribute("success", "Se ha eliminado la editorial.");

        return redirect;
    }

}
