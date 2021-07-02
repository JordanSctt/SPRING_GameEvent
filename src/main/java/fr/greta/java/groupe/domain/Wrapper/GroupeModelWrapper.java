package fr.greta.java.groupe.domain.Wrapper;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.groupe.domain.model.GroupeModel;
import fr.greta.java.groupe.facade.dto.GroupeListDTO;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<GroupeListDTO> fromEntitiesToDto(List<GroupeEntity> listGroupeEntity) throws ApplicationCommunicationException {
        List<GroupeListDTO> groupesListDto = new ArrayList<>();
        for (GroupeEntity entities : listGroupeEntity) {
            groupesListDto.add(fromEntityToDto(entities));
        }
        return groupesListDto;
    }

    public GroupeListDTO fromEntityToDto(GroupeEntity entity) {
        GroupeListDTO dto = new GroupeListDTO();
        dto.setUuid(entity.getId());
        dto.setNom(entity.getNom());
        return dto;
    }

}
