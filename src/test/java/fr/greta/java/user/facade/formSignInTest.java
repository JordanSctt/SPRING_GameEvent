package fr.greta.java.user.facade;


import fr.greta.java.user.facade.UserController;
import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class formSignInTest {

    @Autowired
    UserController userControllerTest;
    @Autowired
    UserRepository userRepository;

    @Test
    public void formSignInTest() {
        UserDTO newUser = new UserDTO();

        newUser.setLogin("testUser");
        newUser.setEmail("mailTest@gmail.com");
        newUser.setPassword("pswTest");
        userControllerTest.formSignIn(newUser);

        Optional <UserEntity> testUser = userRepository.findByLogin("testUser");

        assertTrue(testUser.isPresent());
        assertEquals(newUser.getLogin(), testUser.get().getLogin());

    }

}