package com.example.habittracker.services;

import com.example.habittracker.models.dtos.ProfileDto;

import java.util.List;

public interface ProfileService {

    ProfileDto save (ProfileDto dto);
    ProfileDto findById(Long id);
    List<ProfileDto> findAll();
    ProfileDto update(ProfileDto dto);
    ProfileDto delete (Long id);

}
