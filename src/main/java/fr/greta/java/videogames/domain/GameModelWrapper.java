package fr.greta.java.videogames.domain;

import fr.greta.java.videogames.CustomList;
import fr.greta.java.videogames.persistence.GameEntity;
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
        model.setNote(entity.getNote());
        model.setCommentaire(entity.getCommentaire());
        model.setGenre(entity.getGenre());
        return model;
    }

    public GameEntity toEntity(GameModel model) {
        GameEntity entity = new GameEntity();
        entity.setId(model.getId());
        entity.setTitre(model.getTitre());
        entity.setNote(model.getNote());
        entity.setCommentaire(model.getCommentaire());
        entity.setGenre(model.getGenre());
        return entity;
    }

}
