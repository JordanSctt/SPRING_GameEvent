package fr.greta.java.game.domain.wrapper;

import fr.greta.java.game.CustomList;
import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.groupe.domain.model.GroupeModel;
import fr.greta.java.groupe.facade.dto.GroupeListDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameModelWrapper {


    public CustomList<GameModel, Integer> fromEntitiesPage(Page<GameEntity> entities) {
        CustomList<GameModel, Integer> page = new CustomList<>();
        page.setValue(entities.getTotalPages());
        entities.forEach(entity -> page.getList().add(fromEntity(entity)));
        return page;
    }

    public List<GameModel> fromEntities(List<GameEntity> entities) {
        List<GameModel> models = new ArrayList<>();
        for (GameEntity entity : entities) {
            models.add(fromEntity(entity));
        }
        return models;
    }

    public GameModel fromEntity(GameEntity entity) {
        GameModel model = new GameModel();
        model.setId(entity.getId());
        model.setTitre(entity.getTitre());
        model.setGenre(entity.getGenre());
        return model;
    }

    public GameEntity toEntity(GameModel model) {
        GameEntity entity = new GameEntity();
        entity.setId(model.getId());
        entity.setTitre(model.getTitre());
        entity.setGenre(model.getGenre());
        return entity;
    }

}
