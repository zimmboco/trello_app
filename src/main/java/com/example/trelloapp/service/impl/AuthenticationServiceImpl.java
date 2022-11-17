package com.example.trelloapp.service.impl;

import com.example.trelloapp.model.User;
import com.example.trelloapp.service.AuthenticationService;
import com.example.trelloapp.service.RoleService;
import com.example.trelloapp.service.UserService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.getByName("ROLE_USER")));
        userService.save(user);
        return user;
    }
}
