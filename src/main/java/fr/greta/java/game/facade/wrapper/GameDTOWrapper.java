package fr.greta.java.game.facade.wrapper;


import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.facade.dto.GameDTO;
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

}
