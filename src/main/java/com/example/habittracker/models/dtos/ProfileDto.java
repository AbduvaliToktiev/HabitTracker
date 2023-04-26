package com.example.habittracker.models.dtos;

import com.example.habittracker.models.entities.User;
import com.example.habittracker.models.enums.ColorTheme;
import com.example.habittracker.models.enums.Language;
import com.example.habittracker.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ProfileDto extends BaseDto {

    String name;
    String icon;
    Language language;
    ColorTheme colorTheme;
    User user;
}
