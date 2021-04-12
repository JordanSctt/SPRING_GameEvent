package fr.greta.java.user.domain.service;


import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.groupe.persistence.repository.GroupeRepository;
import fr.greta.java.invitation.persistence.entity.InvitationEntity;
import fr.greta.java.invitation.persistence.repository.InvitationRepository;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private InvitationRepository invitationRepository;

    private static final String USER_ROLE = "USER";

    public Optional<UserEntity> findUserConnected() throws ApplicationServiceException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isConnected(auth)) {
            Optional<UserEntity> userEntity = userRepository.findByLogin(auth.getName());
            if(userEntity.isPresent()){
                return userEntity;
            }
            throw new ApplicationServiceException("User not found");
        }
        throw new ApplicationServiceException("User not connected");
    }

    private boolean isConnected(Authentication auth) {
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE));
    }

    public void sendInvitationToUser(UserEntity userEntity, GroupeEntity groupeEntity)  {
        invitationRepository.save(new InvitationEntity(userEntity, groupeEntity));
        /*if (userEntity.equals(findUserConnected())) {
            groupeEntity.get().getUsers().add(userEntity);
        }*/
    }
}
