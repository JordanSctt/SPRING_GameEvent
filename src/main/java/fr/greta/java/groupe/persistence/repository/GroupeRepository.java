package fr.greta.java.groupe.persistence.repository;

import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupeRepository extends JpaRepository<GroupeEntity, String> {


    @Query("select g FROM GroupeEntity g LEFT JOIN FETCH g.users u where u.id = :userId")
    List<GroupeEntity> findAllByUserId(String userId);

}

