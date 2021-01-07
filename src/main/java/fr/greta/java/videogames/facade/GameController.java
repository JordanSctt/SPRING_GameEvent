package fr.greta.java.videogames.facade;


import fr.greta.java.videogames.domain.CustomPage;
import fr.greta.java.videogames.domain.GameModel;
import fr.greta.java.videogames.domain.GameService;
import fr.greta.java.videogames.persistence.GameEntity;
import fr.greta.java.videogames.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    /*
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("game-list");
        modelAndView.addObject("games", wrapperDTO.fromModels(gameService.findAll()));
        return modelAndView;
    }
     */

    @GetMapping("/list-page/{page}/")
    public ModelAndView findAllWithPaging(@PathVariable(value = "page") int page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        CustomPage<GameModel> all = gameService.findAll(page, sort);

        ModelAndView modelAndView = new ModelAndView("game-list");
        modelAndView.addObject("games", wrapperDTO.fromModels(all.getElements()));
        modelAndView.addObject("paging", (page+1) + " / " + all.getTotalPage());


        modelAndView.addObject("currentPage", page);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView displayFormEdit(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("game-edit");
        modelAndView.addObject("game", wrapperDTO.fromModel(gameService.findById(id)));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute("request") GameDTO request) {
        gameService.save(wrapperDTO.toModel(request));
        return findAllWithPaging(1);
    }

    @GetMapping("/new")
    public ModelAndView displayFormNew() {
        ModelAndView modelAndView = new ModelAndView("game-edit");
        modelAndView.addObject("game", new GameDTO());
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id) {
        GameModel game = new GameModel();
        game.setId(id);
        gameService.delete(game);
        return findAllWithPaging(1);
    }

    @GetMapping("/spec/{title}/")
    public ModelAndView search(@PathVariable(value = "title") String title, int page) {
        Specification<GameEntity> spec = titleIs(title);

        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        Page<GameEntity> all = gameRepository.findAll(spec, PageRequest.of(page, 10, sort));

        //findAll(spec, PageRequest.of(page, 10, sort));

        ModelAndView modelAndView = new ModelAndView("game-list");
        modelAndView.addObject("games", all.getContent());
        modelAndView.addObject("paging", (page+1) + " / " + all.getTotalPages());
        return modelAndView;
    }

    public Specification<GameEntity> titleIs(String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + value + "%");
    }

    /*
    public Specification<UserEntity> emailIs(String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), value);
    }
    */

}
