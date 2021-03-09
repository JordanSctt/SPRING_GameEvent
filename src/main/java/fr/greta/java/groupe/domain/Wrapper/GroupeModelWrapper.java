package fr.greta.java.groupe.domain.Wrapper;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.groupe.domain.model.GroupeModel;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.user.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GroupeModelWrapper {


    public List<GroupeModel> fromEntities(List<GroupeEntity> listGroupeEntity) throws ApplicationCommunicationException {
        List<GroupeModel> groupesModel = new ArrayList<>();
        for (GroupeEntity entities : listGroupeEntity) {
            groupesModel.add(fromEntity(entities));
        }
        return groupesModel;
    }


    public GroupeModel fromEntity(GroupeEntity entity) {
        GroupeModel model = new GroupeModel();
        model.setUuid(entity.getId());
        model.setNom(entity.getNom());
        return model;
    }


}
