package com.example.trelloapp.service;

import com.example.trelloapp.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
