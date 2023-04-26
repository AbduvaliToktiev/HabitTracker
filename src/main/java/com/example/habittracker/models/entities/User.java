package com.example.habittracker.models.entities;

import com.example.habittracker.models.enums.Gender;
import com.example.habittracker.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    @NotNull()
    String username;

    @Column(name = "email", unique = true)
    @NotNull()
    String email;

    @Column(name = "password")
    @NotNull()
    String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @NotNull()
    Gender gender;

    @Column(name = "age")
    @NotNull()
    int age;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    List<Role> roles;



}
