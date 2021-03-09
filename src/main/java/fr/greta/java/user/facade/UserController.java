package fr.greta.java.user.facade;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.game.CustomList;
import fr.greta.java.game.domain.GameService;
import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.facade.GameController;
import fr.greta.java.game.facade.wrapper.GameDTOWrapper;
import fr.greta.java.groupe.domain.Wrapper.GroupeListDTOWrapper;
import fr.greta.java.groupe.domain.service.GroupeService;
import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.facade.wrapper.UserDTOWrapper;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private UserRepository userRepository;
    @Autowired
    private UserDTOWrapper userDTOWrapper;
    @Autowired
    private GameController gameController;
    @Autowired
    private GroupeListDTOWrapper listDTOWrapper;
    @Autowired
    private GroupeService groupeService;
    @Autowired
    private GameService gameService;
    @Autowired
    private GameDTOWrapper wrapperDTO;


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
        userRepository.save(userDTOWrapper.toEntitySignIn(request));
        return accueil();
    }

    @GetMapping("/user/accueil")
    public ModelAndView userAccueil () throws ApplicationCommunicationException {
        return userAccueilWithPage(0);
    }

    @GetMapping("/user/accueil/page")
    public ModelAndView userAccueilWithPage(@RequestParam int page) throws ApplicationCommunicationException {

        ModelAndView modelAndView = new ModelAndView("user-accueil");

        CustomList<GameModel, Integer> all = gameService.findAllByPage(page);
        modelAndView.addObject("games", wrapperDTO.fromModels(all.getList()));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPage", all.getValue());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(name));
        modelAndView.addObject("userConnected", userDTO);

        modelAndView.addObject("groupes", listDTOWrapper.fromModels(groupeService.findAllByUserId(userDTO.getUuid())));

        return modelAndView;
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
    public ModelAndView handleFileUploadUserProfil(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ClassPathResource path = new ClassPathResource("static/images/profil");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(login));
        String pathStr = path.getFile().getAbsolutePath() +  "\\" + userDTO.getUuid() + ".jpg";
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
