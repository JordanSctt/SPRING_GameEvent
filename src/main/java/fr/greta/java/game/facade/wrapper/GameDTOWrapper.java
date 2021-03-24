package fr.greta.java.game.facade.wrapper;

import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.facade.dto.GameDTO;
import fr.greta.java.game.persistence.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GameDTOWrapper {

    public List<GameDTO> fromModels(List<GameModel> models) {
        List<GameDTO> dtos = new ArrayList<>();
        for(GameModel model: models) {
            dtos.add(fromModel(model));
        }
        return dtos;
    }
    /*
    public CustomPage<GameDTO> fromPage(Page<GameEntity> pageEntity) {
        CustomPage<GameDTO> customPage = new CustomPage<>();
        pageEntity.forEach(entity -> customPage.getElements().add(fromEntity(entity)));
        customPage.setTotalPage(pageEntity.getTotalPages());
        return customPage;
    }
     */

    public GameDTO fromEntity(GameEntity entity) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(entity.getId());
        gameDTO.setTitre(entity.getTitre());
        gameDTO.setGenre(entity.getGenre());
        return gameDTO;
    }

    public GameDTO fromModel(GameModel model) {
        GameDTO dto = new GameDTO();
        dto.setId(model.getId());
        dto.setTitre(model.getTitre());
        dto.setGenre(model.getGenre());
        return dto;
    }

    public GameModel toModel(GameDTO dto) {
        GameModel model = new GameModel();
        model.setId(dto.getId());
        model.setTitre(dto.getTitre());
        model.setGenre(dto.getGenre());
        return model;
    }

    public GameEntity toEntity(GameDTO dto) {
        GameEntity entity = new GameEntity();
        entity.setId(dto.getId());
        entity.setTitre(dto.getTitre());
        entity.setGenre(dto.getGenre());
        return entity;
    }

}
