package com.example.habittracker.services;

import com.example.habittracker.models.dtos.ProfileDto;
import com.example.habittracker.models.dtos.ProgressDto;

import java.util.List;

public interface ProgressService {

    ProgressDto save (ProgressDto dto);

    ProgressDto findById(Long id);

    List<ProgressDto> findAll();

    ProgressDto delete (Long id);
}
