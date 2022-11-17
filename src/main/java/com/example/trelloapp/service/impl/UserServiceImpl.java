package com.example.trelloapp.service.impl;

import com.example.trelloapp.model.User;
import com.example.trelloapp.repository.UserRepository;
import com.example.trelloapp.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException("Can`t find user"));
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Can`t find user"));
    }
}
