package com.example.habittracker.services.impl;

import com.example.habittracker.models.entities.Habit;
import com.example.habittracker.repository.HabitRep;
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
class HabitServiceImplTest {

    @Mock
    private HabitRep habitRep;

    @Test
    void save() {
        when(habitRep.save(Mockito.any(Habit.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        Iterable<Habit> habits = habitRep.findAll();
        assertThat(habits).isEmpty();
    }

    @Test
    void update() {
        Habit habit = habitRep.findById(1L).get();

        habit.setName("21 day coding in leetcode");

        Habit habitUpdated = habitRep.save(habit);

        Assertions.assertThat(habitUpdated.getName()).isEqualTo("21 day coding in leetcode");
    }

    @Test
    void delete() {
        Habit habit = habitRep.findById(1L).orElse(null);

        habitRep.delete(habit);

        Habit habit1 = null;

        Optional<Habit> habitOptional = habitRep.findById(1L);

        if (habitOptional.isPresent()) {
            habit1 = habitOptional.get();
        }

        Assertions.assertThat(habit1).isNull();
    }
}