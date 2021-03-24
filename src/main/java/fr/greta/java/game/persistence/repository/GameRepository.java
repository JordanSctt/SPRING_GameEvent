package fr.greta.java.game.persistence.repository;

import fr.greta.java.game.persistence.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface GameRepository extends JpaRepository<GameEntity, Integer>, JpaSpecificationExecutor<GameEntity> {

    Optional<GameEntity> findById(String id);

    /*
    @Query("select g FROM GameEntity g LEFT JOIN FETCH g.users u where u.id = :userId")
    List<GameEntity> findAllGamesByUserId(Pageable of, String userId);
    */

    @Query("select g FROM GameEntity g LEFT JOIN FETCH g.users u where u.id = :userId")
    List<GameEntity> findAllGamesOfUser(String userId);
}
