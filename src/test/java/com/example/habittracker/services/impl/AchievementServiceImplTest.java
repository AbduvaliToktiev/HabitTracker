package com.example.habittracker.services.impl;

import com.example.habittracker.models.entities.Achievement;
import com.example.habittracker.repository.AchievementRep;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AchievementServiceImplTest {

    @Mock
    private AchievementRep achievementRep;

    @Test
    void save() {
        when(achievementRep.save(Mockito.any(Achievement.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        Iterable<Achievement> achievements = achievementRep.findAll();
        assertThat(achievements).isEmpty();
    }

    @Test
    void delete() {
        Achievement achievement = achievementRep.findById(1L).orElse(null);

        achievementRep.delete(achievement);

        Achievement achievement1 = null;

        Optional<Achievement> achievementOptional = achievementRep.findById(1L);

        if (achievementOptional.isPresent()) {
            achievement1 = achievementOptional.get();
        }

        Assertions.assertThat(achievement1).isNull();
    }
}