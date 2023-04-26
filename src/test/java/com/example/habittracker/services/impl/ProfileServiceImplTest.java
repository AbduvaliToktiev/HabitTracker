package com.example.habittracker.services.impl;

import com.example.habittracker.models.entities.Profile;
import com.example.habittracker.models.entities.Progress;
import com.example.habittracker.repository.ProfileRep;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProfileServiceImplTest {

    @Mock
    private ProfileRep profileRep;

    @Test
    void save() {
        when(profileRep.save(Mockito.any(Profile.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        Iterable<Profile> profiles  = profileRep.findAll();
        assertThat(profiles).isEmpty();
    }

    @Test
    void update() {
        Profile profile = profileRep.findById(1L).get();

        profile.setName("Ermek");

        Profile profileUpdated =  profileRep.save(profile);

        Assertions.assertThat(profileUpdated.getName()).isEqualTo("Ermek");
    }

    @Test
    void delete() {
        Profile profile = profileRep.findById(1L).orElse(null);

        profileRep.delete(profile);

        Profile profile1 = null;

        Optional<Profile> profileOptional  = profileRep.findById(1L);

        if(profileOptional.isPresent()){
            profile1 = profileOptional.get();
        }

        Assertions.assertThat(profile1).isNull();
    }
}