package fr.greta.java.game.facade;


import fr.greta.java.config.generic.exception.ApplicationCommunicationException;
import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.groupe.domain.Wrapper.GroupeListDTOWrapper;
import fr.greta.java.groupe.domain.service.GroupeService;
import fr.greta.java.user.facade.UserController;
import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.facade.wrapper.UserDTOWrapper;
import fr.greta.java.user.persistence.repository.UserRepository;
import fr.greta.java.game.CustomList;
import fr.greta.java.game.domain.GameColonne;
import fr.greta.java.game.domain.model.GameModel;
import fr.greta.java.game.domain.GameService;
import fr.greta.java.game.domain.SearchGame;
import fr.greta.java.game.facade.dto.GameDTO;
import fr.greta.java.game.facade.dto.SearchRequestDTO;
import fr.greta.java.game.facade.wrapper.GameDTOWrapper;
import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.game.persistence.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private UserDTOWrapper userDTOWrapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupeService groupeService;
    @Autowired
    private GroupeListDTOWrapper listDTOWrapper;
    @Autowired
    private UserController userController;


    @GetMapping("/tri")
    public ModelAndView pageTri(@RequestParam String colonne) throws ApplicationCommunicationException {
        gameService.setColonne(colonne);
        return userController.userAccueilWithPage(0);
    }

    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute("request") SearchRequestDTO request) throws ApplicationCommunicationException {
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

    @GetMapping("/admin/edit")
    public ModelAndView displayFormEdit(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("user-accueil"); /* "game-list" */
        modelAndView.addObject("game", wrapperDTO.fromModel(gameService.findById(id)));
        return modelAndView;
    }

    @PostMapping("/admin/edit")
    public ModelAndView edit(@ModelAttribute("request") GameDTO request) throws ApplicationCommunicationException {
        gameService.save(wrapperDTO.toModel(request));
        return userController.userAccueilWithPage(0);
    }

    @GetMapping("/new")
    public ModelAndView displayFormNew() {
        ModelAndView modelAndView = new ModelAndView("user-accueil"); /* "game-list" */
        modelAndView.addObject("game", new GameDTO());
        return modelAndView;
    }

    @GetMapping("/admin/delete")
    public ModelAndView delete(@RequestParam String id) throws ApplicationCommunicationException {
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

        ModelAndView modelAndView = new ModelAndView("user-accueil"); /* "game-list" */
        modelAndView.addObject("games", all.getContent());
        modelAndView.addObject("paging", (page+1) + " / " + all.getTotalPages());
        return modelAndView;
    }

    public Specification<GameEntity> titleIs(String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + value + "%");
    }
}
