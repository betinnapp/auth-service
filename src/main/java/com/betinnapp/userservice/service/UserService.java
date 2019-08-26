package com.betinnapp.userservice.service;

import com.betinnapp.userservice.model.User;
import com.betinnapp.userservice.model.UserDTO;
import com.betinnapp.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
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
