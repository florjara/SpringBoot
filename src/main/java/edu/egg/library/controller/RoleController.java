package edu.egg.library.controller;

import edu.egg.library.entity.Role;
import edu.egg.library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping("/form")
    public ModelAndView getForm(){
        ModelAndView mav = new ModelAndView("form-role");
        mav.addObject("role", new Role());
        return mav;
    }
    
    @PostMapping("/create")
    public RedirectView create(Role role){
        RedirectView rv = new RedirectView("/");
        roleService.create(role);
        return rv;
    }
}
