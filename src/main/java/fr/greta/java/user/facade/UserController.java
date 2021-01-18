package fr.greta.java.user.facade;


import fr.greta.java.user.facade.UserDTO;
import fr.greta.java.user.facade.UserDTOWrapper;
import fr.greta.java.user.persistence.UserRepository;
import fr.greta.java.videogames.facade.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDTOWrapper userDTOWrapper;

    @GetMapping("/accueil")
    public ModelAndView accueil() {
        return new ModelAndView("accueil");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("connection");
        return modelAndView;
    }

    @GetMapping("/Signin")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView(("inscription"));
        return modelAndView;
    }

    @PostMapping("/signin")
    public ModelAndView formSignIn(@ModelAttribute("request") UserDTO request) {
        userRepository.save(userDTOWrapper.toEntity(request));
        return accueil();
    }
}
