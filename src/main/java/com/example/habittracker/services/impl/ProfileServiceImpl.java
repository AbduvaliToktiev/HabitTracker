package com.example.habittracker.services.impl;

import com.example.habittracker.mappers.ProfileMapper;
import com.example.habittracker.models.dtos.ProfileDto;
import com.example.habittracker.models.dtos.UserDto;
import com.example.habittracker.models.entities.Profile;
import com.example.habittracker.models.enums.Status;
import com.example.habittracker.repository.ProfileRep;
import com.example.habittracker.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private static final String TOPIC = "NewTopic";

    private final KafkaTemplate<String, ProfileDto> kafkaTemplate;
    private final ProfileRep profileRep;
    private ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    @Override
    public ProfileDto save(ProfileDto dto) {
        kafkaTemplate.send(TOPIC, dto);
        return profileMapper.toDto(profileRep.save(profileMapper.toEntity(dto)));
    }

    @Override
    public ProfileDto findById(Long id) {
        return profileMapper.toDto(profileRep.findById(id)
                .orElseThrow(()->new RuntimeException("Profile is not found")));
    }

    @Override
    public List<ProfileDto> findAll() {
        return profileMapper.toDtos(profileRep.findAll());
    }

    @Override
    public ProfileDto update(ProfileDto dto) {
        Profile profile = profileRep.findById(dto.getId()).orElseThrow(()->new RuntimeException("Profile is not found"));
        profile.setName(dto.getName());
        profile.setLanguage(dto.getLanguage());
        profile.setColorTheme(dto.getColorTheme());
        return profileMapper.toDto(profileRep.save(profile));
    }

    @Override
    public ProfileDto delete(Long id) {
        ProfileDto profileDto = profileMapper.toDto(profileRep.findById(id)
                .orElseThrow(()->new RuntimeException("Profile is not found")));
        profileDto.setStatus(Status.DELETED);
        return save(profileDto);
    }
}
