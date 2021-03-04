package fr.greta.java.game.domain.wrapper;

import fr.greta.java.game.CustomList;
import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.persistence.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GameModelWrapper {


    public CustomList<GameModel, Integer> fromEntities(Page<GameEntity> entities) {
        CustomList<GameModel, Integer> page = new CustomList<>();
        page.setValue(entities.getTotalPages());
        entities.forEach(entity -> page.getList().add(fromEntity(entity)));
        return page;
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
