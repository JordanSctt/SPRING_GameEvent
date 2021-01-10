package fr.greta.java.videogames.facade;

import fr.greta.java.videogames.domain.GameModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameDTOWrapper {

    public List<GameDTO> fromModels(List<GameModel> models) {
        List<GameDTO> dtos = new ArrayList<>();
        for(GameModel model: models) {
            dtos.add(fromModel(model));
        }
        return dtos;
    }

    public GameDTO fromModel(GameModel model) {
        GameDTO dto = new GameDTO();
        dto.setId(model.getId());
        dto.setTitre(model.getTitre());
        dto.setNote(model.getNote());
        dto.setCommentaire(model.getCommentaire());
        dto.setGenre(model.getGenre());
        return dto;
    }

    public GameModel toModel(GameDTO dto) {
        GameModel model = new GameModel();
        model.setId(dto.getId());
        model.setTitre(dto.getTitre());
        model.setNote(dto.getNote());
        model.setCommentaire(dto.getCommentaire());
        model.setGenre(dto.getGenre());
        return model;
    }

}
