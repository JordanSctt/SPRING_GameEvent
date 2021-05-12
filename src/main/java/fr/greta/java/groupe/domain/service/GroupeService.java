package fr.greta.java.groupe.domain.service;

import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.groupe.domain.Wrapper.GroupeModelWrapper;
import fr.greta.java.groupe.domain.model.GroupeModel;
import fr.greta.java.groupe.facade.dto.GroupeUuidDTO;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.groupe.persistence.repository.GroupeRepository;
import fr.greta.java.invitation.domain.service.InvitationService;
import fr.greta.java.user.domain.service.UserService;
import fr.greta.java.user.facade.dto.SearchUserRequestDTO;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupeService {

    @Autowired
    private GroupeModelWrapper groupeModelWrapper;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private InvitationService invitationService;

    public List<GroupeModel> findAllByUserId(String UserId) throws ApplicationCommunicationException {
        return groupeModelWrapper.fromEntities(groupeRepository.findAllByUserId(UserId));
    }


    public void inviteNewUserInGroupe(UserEntity userEntity, GroupeUuidDTO uuid)  {
        Optional<GroupeEntity> groupeEntity = groupeRepository.findById(uuid.getUuid());
        invitationService.sendInvitationToUser(userEntity, groupeEntity.get());
    }
}
