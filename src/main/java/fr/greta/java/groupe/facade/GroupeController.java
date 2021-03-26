package fr.greta.java.groupe.facade;


import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.game.CustomList;
import fr.greta.java.game.domain.GameService;
import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.facade.wrapper.GameDTOWrapper;
import fr.greta.java.groupe.domain.service.GroupeService;
import fr.greta.java.groupe.facade.dto.GroupeDTO;
import fr.greta.java.groupe.facade.dto.GroupeUuidDTO;
import fr.greta.java.groupe.facade.dto.NewGroupeRequestDTO;
import fr.greta.java.groupe.facade.wrapper.GroupeDTOWrapper;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.groupe.persistence.repository.GroupeRepository;
import fr.greta.java.user.domain.service.UserService;
import fr.greta.java.user.facade.dto.SearchUserRequestDTO;
import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.facade.wrapper.UserDTOWrapper;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupeController {


    @Autowired
    private UserDTOWrapper userDTOWrapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupeService groupeService;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private GameDTOWrapper gameDTOWrapper;
    @Autowired
    private GroupeDTOWrapper groupeDTOWrapper;


    @GetMapping("/groupe/accueil")
    public ModelAndView groupeAccueil(@RequestParam("id") String groupeUuid, String message) throws ApplicationServiceException {

        ModelAndView modelAndView = new ModelAndView("groupe-accueil");
        int page = 0;
        CustomList<GameModel, Integer> all = gameService.findAllGameByPage(page);
        modelAndView.addObject("games", gameDTOWrapper.fromModels(all.getList()));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPage", all.getValue());

        GroupeDTO groupeDTO = groupeDTOWrapper.fromEntity(groupeRepository.findById(groupeUuid));
        modelAndView.addObject("groupe", groupeDTO);

        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(userService.findUserConnected().getLogin()));
        modelAndView.addObject("userConnected", userDTO);

        modelAndView.addObject("messageInvitation", message);

        return modelAndView;
    }

    @PostMapping("/groupe/new")
    public ModelAndView saveNewGroupeWithUser(@ModelAttribute("request") NewGroupeRequestDTO nom) {
        try {
            groupeRepository.save(new GroupeEntity(nom.getNom(), List.of(userService.findUserConnected())));
        } catch (ApplicationServiceException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/user/accueil");
    }

    @PostMapping("/groupe/user/new")
    public ModelAndView inviteNewUserInGroupe(@ModelAttribute("request") SearchUserRequestDTO login, GroupeUuidDTO uuid) throws ApplicationServiceException {
        Optional<UserEntity> userEntity = userRepository.findByLogin(login.getLogin());
        String alertInvitation;
        if (userEntity.isPresent()) {
            groupeService.inviteNewUserInGroupe(userEntity.get(), uuid);
            alertInvitation = "Invitation envoyée avec succès !";
        } else {
            alertInvitation = "L'utilisateur n'existe pas !";
        }
        return groupeAccueil(uuid.getUuid(), alertInvitation);
    }

    //----------------------------

    @GetMapping("/file/upload/groupe")
    public ModelAndView displayForm(@RequestParam("id") String groupeUuid) {
        GroupeDTO groupeDTO = groupeDTOWrapper.fromEntity(groupeRepository.findById(groupeUuid));
        ModelAndView modelAndView = new ModelAndView("upload-img-groupe");
        modelAndView.addObject("groupe", groupeDTO);
        return modelAndView;
    }

    @PostMapping("/file/upload/groupe")
    public ModelAndView handleFileUploadUserProfil(@RequestParam("file") MultipartFile multipartFile, @ModelAttribute("request") String groupeUuidDTO) throws IOException {
        ClassPathResource path = new ClassPathResource("static/images/groupe");
        GroupeDTO myGroupeDTO = groupeDTOWrapper.fromEntity(groupeRepository.findById(groupeUuidDTO));
        String pathStr = path.getFile().getAbsolutePath() +  "\\" + myGroupeDTO.getUuid() + ".jpg";
        File destinationFile = new File(pathStr);
        if(!destinationFile.exists()) {
            destinationFile.createNewFile();
        }
        multipartFile.transferTo(destinationFile);
        ModelAndView modelAndView = new ModelAndView("upload-img-groupe");
        modelAndView.addObject("groupe", myGroupeDTO);
        modelAndView.addObject("message", "L'upload de votre photo de groupe s'est éxecuté avec succés");
        return modelAndView;
    }

}
