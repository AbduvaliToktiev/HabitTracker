package com.example.habittracker.services.impl;

import com.example.habittracker.models.entities.User;
import com.example.habittracker.models.enums.Gender;
import com.example.habittracker.models.enums.Status;
import com.example.habittracker.repository.UserRep;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {
    @Mock
    private UserRep userRep;


    @Test
    void register() {
        when(userRep.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void getAll() {
        Iterable<User> users = userRep.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    void findByUsername() {
        User user = new User();
        user.setId(2l);
        user.setCreated(new Date());
        user.setStatus(Status.ACTIVE);
        user.setUpdated(new Date());
        user.setAge(27);
        user.setEmail("springboot1212@gmail.com");
        user.setGender(Gender.MALE);
        user.setPassword("$2a$10$DCj6yq2S9VHM5gS7p3ZRJ.fBCcjl7e4YHzYIodwwFic3IKR6EwpT2");
        user.setUsername("ermek");

        userRep.save(user);

        List<?> queryResult = (List<?>) userRep.findByUsername("ermek");

        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findById() {

    }

    @Test
    void delete() {
        User user = userRep.findById(1L).orElse(null);

        userRep.delete(user);

        User user1 = null;

        Optional<User> optionalEmployee = Optional.ofNullable(userRep.findByEmail("springboot1212@gmail.com"));

        if(optionalEmployee.isPresent()){
            user1 = optionalEmployee.get();
        }

        Assertions.assertThat(user1).isNull();
    }

    @Test
    void resetPassword() {
    }

    @Test
    void changePassword() {
    }
}