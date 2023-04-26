package com.example.habittracker.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tb_achievement")
public class Achievement extends BaseEntity{

    String name;
    String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date date;
    int points;
    boolean isAchievement;
    String result;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;



}

