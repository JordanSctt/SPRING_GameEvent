package fr.greta.java.game.persistence.repository;

import fr.greta.java.game.persistence.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.stream.DoubleStream;


public interface GameRepository extends JpaRepository<GameEntity, Integer>, JpaSpecificationExecutor<GameEntity> {

    Optional<GameEntity> findById(String id);

}
