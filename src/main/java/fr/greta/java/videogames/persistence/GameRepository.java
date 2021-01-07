package fr.greta.java.videogames.persistence;

import fr.greta.java.videogames.domain.CustomPage;
import fr.greta.java.videogames.domain.GameModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GameRepository extends JpaRepository<GameEntity, Integer>, JpaSpecificationExecutor<GameEntity> {

    /*
    public default CustomPage<GameModel> findAllByTitle(int page, Sort sort, Specification spec) {
        return findByFirstName();
    }
    */
    @Query("SELECT g  FROM GameEntity g WHERE g.title LIKE :title")
    List<GameEntity> findByLikeFirstName(String title);

    @Query("SELECT g  FROM GameEntity g WHERE g.title LIKE :title")
    Page<GameEntity> findByFirstName(String title, Pageable page);

}
