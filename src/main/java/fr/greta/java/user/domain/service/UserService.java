package fr.greta.java.user.domain.service;


import fr.greta.java.config.generic.exception.ApplicationServiceException;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private static final String USER_ROLE = "USER";

    public Optional<UserEntity> findUserConnected() throws ApplicationServiceException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isConnected(auth)) {
            Optional<UserEntity> userEntity = userRepository.findByLogin(auth.getName());
            if(userEntity.isPresent()){
                return userEntity;
            }
            throw new ApplicationServiceException("User not found");
        }
        throw new ApplicationServiceException("User not connected");
    }

    private boolean isConnected(Authentication auth) {
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE));
    }

}
