package fr.greta.java.user.facade;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.game.CustomList;
import fr.greta.java.game.domain.GameService;
import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.facade.wrapper.GameDTOWrapper;
import fr.greta.java.groupe.domain.Wrapper.GroupeListDTOWrapper;
import fr.greta.java.groupe.domain.service.GroupeService;
import fr.greta.java.user.domain.service.UserService;
import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.facade.wrapper.UserDTOWrapper;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
    private GroupeListDTOWrapper listDTOWrapper;
    @Autowired
    private GroupeService groupeService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
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
    public ModelAndView userAccueil() throws ApplicationCommunicationException, ApplicationServiceException {
        return userAccueilWithPage(0);
    }

    @GetMapping("/user/accueil/page")
    public ModelAndView userAccueilWithPage(@RequestParam int page) throws ApplicationCommunicationException, ApplicationServiceException {

        ModelAndView modelAndView = new ModelAndView("user-accueil");

        CustomList<GameModel, Integer> allGames = gameService.findAllGameByPage(page);
        modelAndView.addObject("games", wrapperDTO.fromModels(allGames.getList()));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPage", allGames.getValue());

        CustomList<GameModel, Integer> allGamesUser = gameService.findAllGamesOfUserWithPage(userService.findUserConnected().get().getId(), page);
        modelAndView.addObject("gamesUser", wrapperDTO.fromModels(allGamesUser.getList()));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPageGameUser", allGamesUser.getValue());

        UserDTO userDTO = userDTOWrapper.fromEntity(userService.findUserConnected());
        modelAndView.addObject("userConnected", userDTO);
        modelAndView.addObject("groupes", listDTOWrapper.fromModels(groupeService.findAllByUserId(userDTO.getUuid())));

        return modelAndView;
    }

    @GetMapping("/file/upload/user")
    public ModelAndView displayForm() throws ApplicationServiceException {
        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(userService.findUserConnected().get().getLogin()));
        ModelAndView modelAndView = new ModelAndView("upload-img-user");
        modelAndView.addObject("userConnected", userDTO);
        return modelAndView;
    }

    @PostMapping("/file/upload/user")
    public ModelAndView handleFileUploadUserProfil(@RequestParam("file") MultipartFile multipartFile) throws IOException, ApplicationServiceException {
        ClassPathResource path = new ClassPathResource("static/images/profil");
        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(userService.findUserConnected().get().getLogin()));
        String pathStr = path.getFile().getAbsolutePath() +  "\\" + userDTO.getUuid() + ".jpg";
        File destinationFile = new File(pathStr);
        if(!destinationFile.exists()) {
            destinationFile.createNewFile();
        }
        multipartFile.transferTo(destinationFile);
        ModelAndView modelAndView = new ModelAndView("upload-img-user");
        modelAndView.addObject("userConnected", userDTO);
        modelAndView.addObject("message", "L'upload de votre photo de profil s'est éxecuté avec succés");
        return modelAndView;
    }

}
