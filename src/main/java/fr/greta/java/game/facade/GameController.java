package fr.greta.java.game.facade;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.game.domain.wrapper.GameModelWrapper;
import fr.greta.java.user.domain.service.UserService;
import fr.greta.java.user.facade.UserController;
import fr.greta.java.game.domain.GameColonne;
import fr.greta.java.game.domain.GameService;
import fr.greta.java.game.domain.SearchGame;
import fr.greta.java.game.facade.dto.GameDTO;
import fr.greta.java.game.facade.dto.SearchRequestDTO;
import fr.greta.java.game.facade.wrapper.GameDTOWrapper;
import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.game.persistence.repository.GameRepository;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private GameDTOWrapper wrapperDTO;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    @Autowired
    private GameModelWrapper wrapperModel;


    @GetMapping("/tri")
    public ModelAndView pageTri(@RequestParam String colonne) throws ApplicationCommunicationException, ApplicationServiceException {
        gameService.setColonne(colonne);
        return userController.userAccueilWithPage(0);
    }

    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute("request") SearchRequestDTO request) throws ApplicationCommunicationException, ApplicationServiceException {
        gameService.cleanColonnes();

        SearchGame search = new SearchGame();
        search.setTexte(request.getTexte());
        if(request.isRechercheParTitre()) {
            search.getColonnes().add(GameColonne.TITRE);
        }
        if (request.isRechercheParGenre()) {
            search.getColonnes().add(GameColonne.GENRE);
        }
        gameService.setShearch(search);
        return userController.userAccueilWithPage(0);
    }

    @GetMapping("/add")
    public ModelAndView saveGameForUser(@RequestParam String id) throws ApplicationCommunicationException, ApplicationServiceException {
        GameDTO game = wrapperDTO.fromModel(Objects.requireNonNull(gameRepository.findById(id)
                                            .map(entity -> wrapperModel.fromEntity(entity))
                                            .orElse(null)));
        UserEntity user = userService.findUser();
        user.setGames(List.of(wrapperDTO.toEntity(game)));
        userRepository.save(user);
        return userController.userAccueilWithPage(0);
    }

    @GetMapping("/admin/edit")
    public ModelAndView displayFormEdit(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("user-accueil");
        modelAndView.addObject("game", wrapperDTO.fromModel(gameService.findById(id)));
        return modelAndView;
    }

    @PostMapping("/admin/edit")
    public ModelAndView edit(@ModelAttribute("request") GameDTO request) throws ApplicationCommunicationException, ApplicationServiceException {
        gameService.save(wrapperDTO.toModel(request));
        return userController.userAccueilWithPage(0);
    }

    @GetMapping("/new")
    public ModelAndView displayFormNew() {
        ModelAndView modelAndView = new ModelAndView("user-accueil");
        modelAndView.addObject("game", new GameDTO());
        return modelAndView;
    }

    @GetMapping("/admin/delete")
    public ModelAndView delete(@RequestParam String id) throws ApplicationCommunicationException, ApplicationServiceException {
        GameDTO game = new GameDTO();
        game.setId(id);
        gameService.delete(wrapperDTO.toModel(game));
        return userController.userAccueilWithPage(0);
    }

    @GetMapping("/search")
    public ModelAndView search(@PathVariable(value = "title") String title, int page) {
        Specification<GameEntity> spec = titleIs(title);

        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        Page<GameEntity> all = gameRepository.findAll(spec, PageRequest.of(page, 10, sort));

        ModelAndView modelAndView = new ModelAndView("user-accueil");
        modelAndView.addObject("games", all.getContent());
        modelAndView.addObject("paging", (page+1) + " / " + all.getTotalPages());
        return modelAndView;
    }

    public Specification<GameEntity> titleIs(String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + value + "%");
    }
}
