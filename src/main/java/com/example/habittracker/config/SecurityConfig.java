package com.example.habittracker.config;

import com.example.habittracker.security.jwt.JwtConfigurer;
import com.example.habittracker.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private static final String PROFILE_ENDPOINT = "/api/v1/profile/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/**";
    private static final String HABIT_ENDPOINT = "/api/v1/habit/**";
    private static final String PROGRESS_ENDPOINT = "/api/v1/progress/**";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    protected void configure (HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(PROGRESS_ENDPOINT).hasAnyAuthority("ROLE_USER")
                .antMatchers(PROFILE_ENDPOINT, HABIT_ENDPOINT).hasAnyAuthority("ROLE_ADMIN")
                //.anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
