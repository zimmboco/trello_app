package com.example.trelloapp.controller;

import com.example.trelloapp.dto.User;
import com.example.trelloapp.mapper.Mapper;
import com.example.trelloapp.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final Mapper mapper;

    public AuthenticationController(AuthenticationService authenticationService, Mapper mapper) {
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        com.example.trelloapp.model.User userRegister =
                authenticationService.register(user.getEmail(), user.getPassword());
        return mapper.toDto(userRegister);
    }
}
