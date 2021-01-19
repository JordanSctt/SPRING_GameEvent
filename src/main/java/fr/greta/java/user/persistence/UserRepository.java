package fr.greta.java.user.persistence;

import fr.greta.java.user.facade.UserDTO;
import fr.greta.java.user.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByLogin(String login);

    //Optional<UserEntity> findUserByLogin(String login);

}
