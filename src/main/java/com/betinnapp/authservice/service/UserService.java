package com.betinnapp.authservice.service;

import com.betinnapp.authservice.model.User;
import com.betinnapp.authservice.model.dto.UserDTO;
import com.betinnapp.authservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    public UserDTO getUserToken(String email, String password) throws NotFoundException {
        List<User> users = userRepository.findByEmail(email);

        if (users.stream().noneMatch(u -> passwordEncoder.matches(password, u.getPassword()))) {
            throw new NotFoundException("USER_NOT_FOUND");
        }

        UUID random = UUID.randomUUID();
        User user = users.get(0);
        user.setToken(random);

        userRepository.save(user);

        return objectMapper.convertValue(user, UserDTO.class);
    }
}
