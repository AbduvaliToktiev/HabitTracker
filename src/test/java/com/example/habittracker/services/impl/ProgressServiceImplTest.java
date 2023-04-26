package com.example.habittracker.services.impl;

import com.example.habittracker.models.entities.Progress;
import com.example.habittracker.repository.ProgressRep;
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
class ProgressServiceImplTest {

    @Mock
    private ProgressRep progressRep;

    @Test
    void save() {
        when(progressRep.save(Mockito.any(Progress.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        Iterable<Progress> progresses  = progressRep.findAll();
        assertThat(progresses).isEmpty();
    }

    @Test
    void delete() {
        Progress progress = progressRep.findById(1L).orElse(null);

        progressRep.delete(progress);

        Progress progress1 = null;

        Optional<Progress> progressOptional  = progressRep.findById(1L);

        if(progressOptional.isPresent()){
            progress1 = progressOptional.get();
        }

        Assertions.assertThat(progress1).isNull();
    }
}