package com.example.habittracker.security;

import com.example.habittracker.models.entities.User;
import com.example.habittracker.security.jwt.JwtUser;
import com.example.habittracker.security.jwt.JwtUserFactory;
import com.example.habittracker.services.UserService;
import lombok.RequiredArgsConstructor;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User with username " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
