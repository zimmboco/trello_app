package com.example.trelloapp.controller;

import com.example.trelloapp.dto.User;
import com.example.trelloapp.mapper.Mapper;
import com.example.trelloapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
       return mapper.toDto(userService.save(mapper.toModel(user)));
    }

    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        com.example.trelloapp.model.User user = userService.findByEmail(email);
        return ResponseEntity.ok().body(mapper.toDto(user));
    }
}
