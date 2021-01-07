package fr.greta.java.videogames.domain;

import fr.greta.java.videogames.persistence.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameModelWrapper {


    public CustomPage<GameModel> fromPage(Page<GameEntity> pageEntity) {
        CustomPage<GameModel> customPage = new CustomPage<>();
        pageEntity.forEach(entity -> customPage.getElements().add(fromEntity(entity)));
        customPage.setTotalPage(pageEntity.getTotalPages());
        return customPage;
    }

    public List<GameModel> fromEntities(Iterable<GameEntity> entities) {
        List<GameModel> list = new ArrayList<>();
        entities.forEach(entity -> list.add(fromEntity(entity)));
        return list;
    }

    public GameModel fromEntity(GameEntity entity) {
        GameModel model = new GameModel();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setNote(entity.getNote());
        model.setCommentaire(entity.getCommentaire());
        model.setGenre(entity.getGenre());
        return model;
    }

    public GameEntity toEntity(GameModel model) {
        GameEntity entity = new GameEntity();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setNote(model.getNote());
        entity.setCommentaire(model.getCommentaire());
        entity.setGenre(model.getGenre());
        return entity;
    }

}
