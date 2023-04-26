package com.example.habittracker.services.impl;

import com.example.habittracker.mappers.ResetPassMapper;
import com.example.habittracker.mappers.UserMapper;
import com.example.habittracker.models.dtos.UserDto;
import com.example.habittracker.models.entities.Role;
import com.example.habittracker.models.entities.User;
import com.example.habittracker.models.enums.Status;
import com.example.habittracker.repository.ResetPassRep;
import com.example.habittracker.repository.RoleRep;
import com.example.habittracker.repository.UserRep;
import com.example.habittracker.security.passwordDto.ChangePassDto;
import com.example.habittracker.security.passwordDto.ResetPassDto;
import com.example.habittracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String TOPIC = "NewTopic";

    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    private final UserRep userRep;
    private final RoleRep roleRep;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper = UserMapper.INSTANCE;
    private final JavaMailSender mailSender;
    private final ResetPassRep resetPassRep;
    private ResetPassMapper passMapper = ResetPassMapper.INSTANCE;


    @Override
    public UserDto register(UserDto userDto) {
        kafkaTemplate.send(TOPIC, userDto);
        return userMapper.toDto(userRep.save(toEntity(userDto)));
    }

    @Override
    public List<User> getAll() {
        return userRep.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRep.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRep.findById(id).orElseThrow(() -> new RuntimeException("User is not found"));
    }

    @Override
    public void delete(Long id) {
        userRep.deleteById(id);
    }

    @Override
    public void resetPassword(ResetPassDto resetPassDto) {
        settingEmail(passMapper.toDto(resetPassRep.save(passMapper.toEntity(resetPassDto))));
    }

    @Override
    public void changePassword(ChangePassDto changePassDto) {
        User user = userRep.findByEmail(changePassDto.getEmail());
        user.setPassword(passwordEncoder.encode(changePassDto.getPassword()));
        userRep.save(user);
    }

    private User toEntity(UserDto userDto) {

        Role roleUser = roleRep.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setAge(userDto.getAge());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        return user;
    }

    private void settingEmail(ResetPassDto dto){
        String textSend = "Username: " + dto.getUsername() +
                "\n Token: " + dto.getResetToken();
        String subject = "Reset password username: " + dto.getUsername() + " dataTime: " + new Date();
        String email = dto.getEmail();
        try {
            sendEmail(email, subject, textSend);
            System.out.println("Message sent successfully....");
        } catch (Exception e) {
            throw new RuntimeException("Error sending email");
        }

    }

    private void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
