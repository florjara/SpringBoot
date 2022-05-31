package edu.egg.library.controller;

import edu.egg.library.entity.User;
import edu.egg.library.service.UserService;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) { //no existe un metodo post para login pq el form lo maneja logincheck
        ModelAndView mav = new ModelAndView("login-form");

        if (error != null) {
            mav.addObject("error", "Invalid email or password"); //cuando el logueo falla, redirecciona nuevamente al loguin con info en la ruta ?error=true config de appSecurity
        }
        if (logout != null) {
            mav.addObject("logout", "You have successfully exited the platform");//cuando el logout es exitoso, redirecciona nuevamente al loguin con info en la ruta ?logout=true config de appSecurity
        }
        if (principal != null) {
            mav.setViewName("redirect:/"); // maneja info de sesiones, si no esta vacio significa que hay una sesion iniciada, por ende redirecciona al home  
        }
        return mav;
    }

    @GetMapping("/sign-up")
    public ModelAndView signUp(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("sign-up-form");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) {
            mav.setViewName("redirect:/");
        }

        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("user", inputFlashMap.get("user"));
        } else {
            mav.addObject("user", new User());
        }

        return mav;
    }

    @PostMapping("/register")
    public RedirectView register(User userDto, RedirectAttributes attributes) {
        RedirectView rv = new RedirectView("/");
        try {
            userService.create(userDto);
            attributes.addFlashAttribute("success", "Se ha registrado.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("exception", "No se pudo crear lanzo un illegal");
            attributes.addFlashAttribute("user", userDto);
            rv.setUrl("/auth/sign-up");
        }

        return rv;
    }
}
