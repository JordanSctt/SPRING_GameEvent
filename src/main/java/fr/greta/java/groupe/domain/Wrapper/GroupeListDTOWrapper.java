package fr.greta.java.groupe.domain.Wrapper;

import fr.greta.java.groupe.domain.model.GroupeModel;
import fr.greta.java.groupe.facade.dto.GroupeListDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupeListDTOWrapper {


    public List<GroupeListDTO> fromModels(List<GroupeModel> models) {
        List<GroupeListDTO> dtos = new ArrayList<>();
        for (GroupeModel model : models) {
            dtos.add(fromModel(model));
        }
        return dtos;
    }

    public GroupeListDTO fromModel(GroupeModel model) {
        GroupeListDTO dto = new GroupeListDTO();
        dto.setUuid(model.getUuid());
        dto.setNom(model.getNom());
        return dto;
    }

}
