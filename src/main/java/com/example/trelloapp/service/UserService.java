package com.example.trelloapp.service;

import com.example.trelloapp.model.User;

public interface UserService {
    User save(User user);

    User get(Long id);

    void deleteById(Long id);

    User findByEmail(String email);

    User getUser(String email);
}
