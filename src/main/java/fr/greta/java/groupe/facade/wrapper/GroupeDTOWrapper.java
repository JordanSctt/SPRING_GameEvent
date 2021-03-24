package fr.greta.java.groupe.facade.wrapper;


import fr.greta.java.game.facade.dto.GameDTO;
import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.groupe.facade.dto.GroupeDTO;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.facade.wrapper.UserDTOWrapper;
import fr.greta.java.user.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GroupeDTOWrapper {

    @Autowired
    private UserDTOWrapper userDTOWrapper;

    public GroupeDTO fromEntity(Optional<GroupeEntity> entity) {
        GroupeDTO groupeDTO = new GroupeDTO();
        groupeDTO.setUuid(entity.get().getId());
        groupeDTO.setNom(entity.get().getNom());
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserEntity users : entity.get().getUsers()) {
            userDTOList.add(userDTOWrapper.fromEntity(Optional.ofNullable(users)));
        }
        groupeDTO.setUsers(userDTOList);
        return groupeDTO;
    }

}
