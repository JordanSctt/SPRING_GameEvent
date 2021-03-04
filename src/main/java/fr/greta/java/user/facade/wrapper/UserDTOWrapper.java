package fr.greta.java.user.facade.wrapper;

import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.persistence.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOWrapper {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder().encode(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }
}
