package com.betinnapp.userservice.controller;

import com.betinnapp.userservice.model.User;
import com.betinnapp.userservice.model.UserDTO;
import com.betinnapp.userservice.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping(path = "auth")
public class AuthController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(path = "/login")
    public UserDTO getUserById(@RequestHeader(name = "authorization") String authorization) throws NotFoundException {

        byte[] decode = Base64.getDecoder().decode(authorization.replace("Basic ", ""));
        String decodedString = new String(decode);
        String[] split = decodedString.split(":");

        return userService.getUserToken(split[0],split[1]);
    }
}
