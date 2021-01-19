package fr.greta.java.videogames.facade;


import fr.greta.java.user.facade.UserDTO;
import fr.greta.java.user.facade.UserDTOWrapper;
import fr.greta.java.user.persistence.UserRepository;
import fr.greta.java.videogames.CustomList;
import fr.greta.java.videogames.domain.GameColonne;
import fr.greta.java.videogames.domain.GameModel;
import fr.greta.java.videogames.domain.GameService;
import fr.greta.java.videogames.domain.SearchGame;
import fr.greta.java.videogames.persistence.GameEntity;
import fr.greta.java.videogames.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
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
    private GameDTOWrapper gameDTOWrapper;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTOWrapper userDTOWrapper;


    @GetMapping("/list")
    public ModelAndView list() {
        return findAllWithPaging(0);
    }

    @GetMapping("/tri")
    public ModelAndView pageTri(@RequestParam String colonne) {
        gameService.setColonne(colonne);
        return findAllWithPaging(0);
    }

    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute("request") SearchRequestDTO request) {
        gameService.cleanColonnes();

        SearchGame search = new SearchGame();
        search.setTexte(request.getTexte());
        if(request.isRechercheParTitre()) {
            search.getColonnes().add(GameColonne.TITRE);
        }
        if(request.isRechercheParCommentaire()) {
            search.getColonnes().add(GameColonne.COMMENTAIRE);
        }
        if (request.isRechercheParGenre()) {
            search.getColonnes().add(GameColonne.GENRE);
        }
        gameService.setShearch(search);
        return findAllWithPaging(0);
    }

    @GetMapping("/page")
    public ModelAndView findAllWithPaging(@RequestParam int page) {
        CustomList<GameModel, Integer> all = gameService.findAllByPage(page);

        ModelAndView modelAndView = new ModelAndView("game-list");
        modelAndView.addObject("games", gameDTOWrapper.fromModels(all.getList()));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPage", all.getValue());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        modelAndView.addObject("nameUserConnected", login);

        UserDTO userDTO = userDTOWrapper.fromEntity(userRepository.findByLogin(login));
        modelAndView.addObject("userConnected", userDTO);

        return modelAndView;
    }

    @GetMapping("/admin/edit")
    public ModelAndView displayFormEdit(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("game-edit");
        modelAndView.addObject("game", gameDTOWrapper.fromModel(gameService.findById(id)));
        return modelAndView;
    }

    @PostMapping("/admin/edit")
    public ModelAndView edit(@ModelAttribute("request") GameDTO request) {
        gameService.save(gameDTOWrapper.toModel(request));
        return findAllWithPaging(1);
    }

    @GetMapping("/new")
    public ModelAndView displayFormNew() {
        ModelAndView modelAndView = new ModelAndView("game-edit");
        modelAndView.addObject("game", new GameDTO());
        return modelAndView;
    }

    @GetMapping("/admin/delete")
    public ModelAndView delete(@RequestParam int id) {
        GameDTO game = new GameDTO();
        game.setId(id);
        gameService.delete(gameDTOWrapper.toModel(game));
        return findAllWithPaging(1);
    }

    @GetMapping("/search")
    public ModelAndView search(@PathVariable(value = "title") String title, int page) {
        Specification<GameEntity> spec = titleIs(title);

        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        Page<GameEntity> all = gameRepository.findAll(spec, PageRequest.of(page, 10, sort));

        ModelAndView modelAndView = new ModelAndView("game-list");
        modelAndView.addObject("games", all.getContent());
        modelAndView.addObject("paging", (page+1) + " / " + all.getTotalPages());
        return modelAndView;
    }

    public Specification<GameEntity> titleIs(String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + value + "%");
    }


}
