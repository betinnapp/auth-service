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

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    ObjectMapper mapper = new ObjectMapper();

    public UserDTO getUserToken(String email, String password) throws NotFoundException {

        List<User> users = userRepository.findByEmail(email);
        User authenticatedUser = null;

        for (User u : users) {
            if (passwordEncoder.matches(password, u.getPassword())) {
                authenticatedUser = u;
            }
        }

        if (authenticatedUser == null) {
            throw new NotFoundException("USER_NOT_FOUND");
        }

        UUID random = UUID.randomUUID();
        User user = users.get(0);
        user.setToken(random);
        userRepository.save(user);

        return mapper.convertValue(user, UserDTO.class);
    }
}
