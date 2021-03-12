package fr.greta.java.groupe.facade;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.game.CustomList;
import fr.greta.java.game.domain.GameService;
import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.facade.wrapper.GameDTOWrapper;
import fr.greta.java.groupe.facade.dto.NewGroupeRequestDTO;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.groupe.persistence.repository.GroupeRepository;
import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.facade.wrapper.UserDTOWrapper;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/groupe")
public class GroupeController {

    private static final String USER_ROLE = "USER";

    @Autowired
    private UserDTOWrapper userDTOWrapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private GameDTOWrapper wrapperDTO;


    @GetMapping("/accueil")
    public ModelAndView groupeAccueil() {
        return groupeAccueilWithPage(0);
    }

    @GetMapping("/accueil/page")
    public ModelAndView groupeAccueilWithPage(@RequestParam int page) {

        ModelAndView modelAndView = new ModelAndView("groupe-accueil");

        CustomList<GameModel, Integer> all = gameService.findAllGameByPage(page);
        modelAndView.addObject("games", wrapperDTO.fromModels(all.getList()));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPage", all.getValue());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(name));
        modelAndView.addObject("userConnected", userDTO);

        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView saveNewGroupeWithUser(@ModelAttribute("request") NewGroupeRequestDTO nom) {
        try {
            groupeRepository.save(new GroupeEntity(nom.getNom(), List.of(findUser())));
        } catch (ApplicationServiceException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/user/accueil");
    }

    //----------------------------
    private UserEntity findUser() throws ApplicationServiceException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isConnected(auth)) {
            Optional<UserEntity> userEntity = userRepository.findByLogin(auth.getName());
            if(userEntity.isPresent()){
                return userEntity.get();
            }
            throw new ApplicationServiceException("User not found");

        }
        throw new ApplicationServiceException("User not connected");
    }

    private boolean isConnected(Authentication auth) {
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE));
    }

}
