package com.example.habittracker.services;

import com.example.habittracker.models.dtos.HabitDto;

import java.util.List;

public interface HabitService {

    HabitDto save (HabitDto dto);
    HabitDto findById(Long id);
    List<HabitDto> findAll();
    HabitDto update(HabitDto dto);
    HabitDto delete (Long id);
}
