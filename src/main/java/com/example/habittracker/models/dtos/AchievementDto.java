package com.example.habittracker.models.dtos;

import com.example.habittracker.models.entities.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AchievementDto extends BaseDto{
    String name;
    String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date date;
    int points;
    boolean isAchievement;
    String result;
    @JsonIgnore
    Profile profile;
}
