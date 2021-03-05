package fr.greta.java.user.facade.wrapper;

import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.persistence.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDTOWrapper {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getUuid());
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder().encode(dto.getPassword()));
        entity.setRole("USER");
        return entity;
    }

    public UserDTO fromEntity(Optional<UserEntity> entity) {
        UserDTO dto = new UserDTO();
        dto.setUuid(entity.get().getId());
        dto.setLogin(entity.get().getLogin());
        dto.setEmail(entity.get().getEmail());
        dto.setRole(entity.get().getRole());
        return dto;
    }
}
