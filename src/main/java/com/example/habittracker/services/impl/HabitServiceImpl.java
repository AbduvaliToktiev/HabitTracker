package com.example.habittracker.services.impl;

import com.example.habittracker.mappers.HabitMapper;
import com.example.habittracker.models.dtos.HabitDto;
import com.example.habittracker.models.dtos.UserDto;
import com.example.habittracker.models.entities.Habit;
import com.example.habittracker.models.enums.Status;
import com.example.habittracker.repository.HabitRep;
import com.example.habittracker.services.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private static final String TOPIC = "NewTopic";

    private final KafkaTemplate<String, HabitDto> kafkaTemplate;
    private final HabitRep habitRep;
    private HabitMapper habitMapper = HabitMapper.INSTANCE;

    @Override
    public HabitDto save(HabitDto dto) {
        kafkaTemplate.send(TOPIC, dto);
        return habitMapper.toDto(habitRep.save(habitMapper.toEntity(dto)));
    }

    @Override
    public HabitDto findById(Long id) {
        return habitMapper.toDto(habitRep.findById(id)
                .orElseThrow(()->new RuntimeException("Habit is not found")));
    }

    @Override
    public List<HabitDto> findAll() {
        return habitMapper.toDtos(habitRep.findAll());
    }

    @Override
    public HabitDto update(HabitDto dto) {
        Habit habit = habitRep.findById(dto.getId())
                .orElseThrow(()->new RuntimeException("Habit is not found"));
        habit.setName(dto.getName());
        habit.setDescription(dto.getDescription());
        habit.setTarget(dto.getTarget());
        habit.setFrequency(dto.getFrequency());
        habit.setStartDate(dto.getStartDate());
        habit.setEndDate(dto.getEndDate());
        return habitMapper.toDto(habitRep.save(habit));
    }

    @Override
    public HabitDto delete(Long id) {
        HabitDto habitDto = habitMapper.toDto(habitRep.findById(id)
                .orElseThrow(()->new RuntimeException("Habit is not found")));
        habitDto.setStatus(Status.DELETED);
        return save(habitDto);
    }
}
