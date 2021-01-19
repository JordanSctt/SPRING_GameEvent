package fr.greta.java.user.facade;


import fr.greta.java.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

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

    @GetMapping("/file/upload")
    public ModelAndView displayForm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(login));
        ModelAndView modelAndView = new ModelAndView("upload-img");
        modelAndView.addObject("userConnected", userDTO);
        return modelAndView;
    }

    @PostMapping("/file/upload")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        ClassPathResource path = new ClassPathResource("static/images/profil");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(login));

        String pathStr = path.getFile().getAbsolutePath() +  "\\" + userDTO.getId() + ".jpg";
        File destinationFile = new File(pathStr);
        if(!destinationFile.exists()) {
            destinationFile.createNewFile();
        }
        multipartFile.transferTo(destinationFile);

        ModelAndView modelAndView = new ModelAndView("upload-img");
        modelAndView.addObject("userConnected", userDTO);
        modelAndView.addObject("message", "L'upload de votre photo de profil s'est éxecuté avec succés");

        return modelAndView;
    }
}
