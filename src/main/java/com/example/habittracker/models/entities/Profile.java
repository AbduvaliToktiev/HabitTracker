package com.example.habittracker.models.entities;

import com.example.habittracker.models.enums.ColorTheme;
import com.example.habittracker.models.enums.Language;
import com.example.habittracker.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tb_profile")
public class Profile extends BaseEntity{

    @Column(name = "name")
    String name;
    @Column(name = "icon")
    String icon;
    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    Language language;
    @Enumerated(EnumType.STRING)
    @Column(name = "color_theme")
    ColorTheme colorTheme;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
