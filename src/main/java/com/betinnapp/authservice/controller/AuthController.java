package com.betinnapp.authservice.controller;

import com.betinnapp.authservice.model.dto.UserDTO;
import com.betinnapp.authservice.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping(path = "auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping(path = "/login")
    public UserDTO getUserById(@RequestHeader(name = "authorization") String authorization) throws NotFoundException {
        byte[] decode = Base64.getDecoder().decode(authorization.replace("Basic ", ""));
        String decodedString = new String(decode);
        String[] split = decodedString.split(":");

        return userService.getUserToken(split[0],split[1]);
    }
}
