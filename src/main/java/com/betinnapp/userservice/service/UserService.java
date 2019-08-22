package com.betinnapp.userservice.service;

import com.betinnapp.userservice.model.User;
import com.betinnapp.userservice.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User getUserToken(String email, String password) throws NotFoundException {
        List<User> users = userRepository.findByEmailAndPassword(email, password);

        if (users.isEmpty()) {
            throw new NotFoundException("USER_NOT_FOUND");
        }

        UUID random = UUID.randomUUID();

        User user = users.get(0);
        user.setToken(random);
        userRepository.save(user);

        return user;
    }
}
