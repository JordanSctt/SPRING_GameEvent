package fr.greta.java.user.facade;

import fr.greta.java.user.persistence.UserEntity;
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
        entity.setId(dto.getId());
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder().encode(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }

    public UserDTO fromEntity(Optional<UserEntity> entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.get().getId());
        dto.setLogin(entity.get().getLogin());
        dto.setEmail(entity.get().getEmail());
        dto.setFirstName(entity.get().getFirstName());
        dto.setLastName(entity.get().getLastName());
        dto.setRole(entity.get().getRole());
        return dto;
    }

}
