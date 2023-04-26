package com.example.habittracker.services.impl;

import com.example.habittracker.mappers.AchievementMapper;
import com.example.habittracker.mappers.HabitMapper;
import com.example.habittracker.mappers.ProgressMapper;
import com.example.habittracker.models.dtos.AchievementDto;
import com.example.habittracker.models.dtos.HabitDto;
import com.example.habittracker.models.dtos.ProgressDto;
import com.example.habittracker.models.entities.Achievement;
import com.example.habittracker.models.entities.Profile;
import com.example.habittracker.models.enums.Status;
import com.example.habittracker.repository.AchievementRep;
import com.example.habittracker.repository.HabitRep;
import com.example.habittracker.repository.ProfileRep;
import com.example.habittracker.repository.ProgressRep;
import com.example.habittracker.services.AchievementService;
import com.example.habittracker.services.HabitService;
import com.example.habittracker.services.ProgressService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private static final String TOPIC = "NewTopic";

    private final KafkaTemplate<String, AchievementDto> kafkaTemplate;
    private final AchievementRep achievementRep;
    private AchievementMapper achMapper = AchievementMapper.INSTANCE;
    /*private final HabitRep habitRep;
    private HabitMapper habitMapper = HabitMapper.INSTANCE;*/
    private final HabitService habitService;
    /*private final ProgressService progressService;*/
    private final ProgressRep progressRep;

    private ProgressMapper progressMapper = ProgressMapper.INSTANCE;
    private final ProfileRep profileRep;


    @Override
    public AchievementDto save(Long profileId, Long habitId) {

        Profile profile = profileRep.findById(profileId).get();
        List<ProgressDto> progressDtos = progressMapper.toDtos(progressRep.findAllByProfile(profileId));
        HabitDto habitDto = habitService.findById(habitId);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(habitDto.getStartDate());
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        int count = 0;
        for (int i = 0; i<progressDtos.size(); i++) {

            ProgressDto item = progressDtos.get(i);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Calendar calendarProgress = Calendar.getInstance();
            calendarProgress.setTime(item.getProgressDate());

            if (item.getTarget() >= habitDto.getTarget() &&
                    calendarProgress.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    calendarProgress.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                count++;
            }
        }

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(habitDto.getStartDate());
        endDate.setTime(habitDto.getEndDate());
        endDate.add(Calendar.DAY_OF_MONTH, 1);
        long diffInMillis = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        Long diffInDay = TimeUnit.MILLISECONDS.toDays(diffInMillis);

        AchievementDto achievementDto = new AchievementDto();
        achievementDto.setName(habitDto.getName());
        achievementDto.setDescription(habitDto.getDescription());
        achievementDto.setDate(new Date());
        achievementDto.setProfile(profile);

        System.out.println("diff "+ diffInDay);
        System.out.println("count "+ count);

        if(count == diffInDay){
            achievementDto.setPoints(100);
            achievementDto.setAchievement(true);
            achievementDto.setResult("Вы молодец! Цель достигнута и награда получена. Теперь можно приступать к следующей задаче.");
        }else{
            achievementDto.setPoints(0);
            achievementDto.setResult("К сожалению, вы не выполнили свою привычку и не получили достижений.");
        }

        Achievement achievement = achMapper.toEntity(achievementDto);
        achievement = achievementRep.save(achievement);

        kafkaTemplate.send(TOPIC, achievementDto);

        return achMapper.toDto(achievement);

    }

    @Override
    public AchievementDto findById(Long id) {
        return achMapper.toDto(achievementRep.findById(id)
                .orElseThrow(()->new RuntimeException("Achievement not found")));
    }

    @Override
    public List<AchievementDto> findAll() {
        return achMapper.toDtos(achievementRep.findAll());
    }

    @Override
    public AchievementDto delete(Long id) {
        AchievementDto achievementDto = achMapper.toDto(achievementRep.findById(id)
                .orElseThrow(()->new RuntimeException("Achievement not found")));
        achievementDto.setStatus(Status.DELETED);
        return achMapper.toDto(achievementRep.save(achMapper.toEntity(achievementDto)));
    }
}
