package fr.greta.java.invitation.domain.service;

import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.groupe.domain.Wrapper.GroupeListDTOWrapper;
import fr.greta.java.groupe.domain.Wrapper.GroupeModelWrapper;
import fr.greta.java.groupe.facade.dto.GroupeListDTO;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.invitation.persistence.entity.InvitationEntity;
import fr.greta.java.invitation.persistence.repository.InvitationRepository;
import fr.greta.java.user.domain.service.UserService;
import fr.greta.java.user.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class InvitationService {

    @Autowired
    private UserService userService;
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private GroupeModelWrapper groupeModelWrapper;
    @Autowired
    private GroupeListDTOWrapper groupeListDTOWrapper;

    public void sendInvitationToUser(UserEntity userEntity, GroupeEntity groupeEntity) {
        Optional<InvitationEntity> invitation = invitationRepository.findByUserIdAndGroupeId(userEntity.getId(), groupeEntity.getId());
        if (invitation.isEmpty()) {
            invitationRepository.save(new InvitationEntity(userEntity, groupeEntity));
        }
    }

    public List<GroupeListDTO> findGroupesInvitations() throws ApplicationServiceException {
        List<InvitationEntity> listInvitationsOfUser = findInvitationsUser();
        List<GroupeListDTO> groupeListDTOS = new ArrayList<>();
        for (InvitationEntity invitation : listInvitationsOfUser) {
            groupeListDTOS.add(groupeModelWrapper.fromEntityToDto(invitation.getGroupe()));
        }
        return groupeListDTOS;
    }

    public List<InvitationEntity> findInvitationsUser() throws ApplicationServiceException {
        Optional<UserEntity> userConnected = userService.findUserConnected();
        List<InvitationEntity> listInvitations = userConnected.get().getInvitations();
        return listInvitations;
    }
}
