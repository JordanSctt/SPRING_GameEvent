package fr.greta.java.groupe.facade;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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


    @PostMapping("/new")
    public ModelAndView saveNewGroupeWithUser(@ModelAttribute("request") NewGroupeRequestDTO nom) throws ApplicationCommunicationException {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        if (isConnected(authUser)) {
            UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(authUser.getName()));
            UserEntity userEntity = userDTOWrapper.toEntity(userDTO);
            groupeRepository.save(new GroupeEntity(nom.getNom(), List.of(userEntity)));
        } else {
            throw new ApplicationCommunicationException("User not found");
        }
        return new ModelAndView("redirect:/user/accueil");
    }


    //----------------------------
    private boolean isConnected(Authentication auth) {
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE));
    }

}
