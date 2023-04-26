package com.example.habittracker.security.jwt;

import com.example.habittracker.models.entities.Role;
import com.example.habittracker.models.entities.User;
import com.example.habittracker.models.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getGender(),
                user.getAge(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
