package com.example.habittracker.models.entities;

import com.example.habittracker.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @Column(name = "name", unique = true)
    @NotNull
    String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<User> users;

    @PrePersist
    protected void onCreate() {
        status = Status.ACTIVE;
    }
}
