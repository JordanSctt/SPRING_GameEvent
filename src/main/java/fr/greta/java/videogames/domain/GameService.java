package fr.greta.java.videogames.domain;


import fr.greta.java.videogames.persistence.GameEntity;
import fr.greta.java.videogames.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {


    @Autowired
    private GameRepository repository;

    @Autowired
    private GameModelWrapper wrapperModel;


    public CustomPage<GameModel> findAll(int page, Sort sort) {
        return wrapperModel.fromPage(repository.findAll(PageRequest.of(page, 10, sort)));
    }

    public GameModel findById(int id) {
        return repository.findById(id)
                .map(entity -> wrapperModel.fromEntity(entity))
                .orElse(null);
    }

    public GameModel save(GameModel model) {
        GameEntity entity = wrapperModel.toEntity(model);
        repository.save(entity);
        return wrapperModel.fromEntity(entity);
    }

    public void delete(GameModel model) {
        repository.delete(wrapperModel.toEntity(model));
    }


}
