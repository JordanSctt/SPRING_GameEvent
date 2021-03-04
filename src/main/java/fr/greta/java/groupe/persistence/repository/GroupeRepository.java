package fr.greta.java.groupe.persistence.repository;

import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends JpaRepository<GroupeEntity, String> {



}
