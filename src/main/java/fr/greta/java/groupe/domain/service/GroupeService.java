package fr.greta.java.groupe.domain.service;

import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.groupe.domain.Wrapper.GroupeModelWrapper;
import fr.greta.java.groupe.domain.model.GroupeModel;
import fr.greta.java.groupe.persistence.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeService {

    @Autowired
    private GroupeModelWrapper groupeModelWrapper;
    @Autowired
    private GroupeRepository groupeRepository;

    public List<GroupeModel> findAllByUserId(String UserId) throws ApplicationCommunicationException {
        return groupeModelWrapper.fromEntities(groupeRepository.findAllByUserId(UserId));
    }

}
