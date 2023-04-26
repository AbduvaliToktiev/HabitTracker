package com.example.habittracker.mappers;

import com.example.habittracker.models.dtos.HabitDto;
import com.example.habittracker.models.entities.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HabitMapper extends BaseMapper<Habit, HabitDto> {

    HabitMapper INSTANCE = Mappers.getMapper(HabitMapper.class);
}
