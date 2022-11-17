package com.example.trelloapp.security;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.trelloapp.model.Role;
import com.example.trelloapp.model.User;
import com.example.trelloapp.repository.UserRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class CustomUserDetailsServiceTest {
    private UserDetailsService userDetailsService;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        String email = "zimmboco@gmail.com";
        user.setEmail(email);
        user.setPassword("123456");
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(userRole));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails actual = userDetailsService.loadUserByUsername(email);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(email, actual.getUsername());
        System.out.println("test");
    }

    @Test
    void loadUserByUsername_UsernameNotFound() {
        User user = new User();
        String email = "zimmboco@gmail.com";
        user.setEmail(email);
        user.setPassword("123456");
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(userRole));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        try {
            userDetailsService.loadUserByUsername("email");
        } catch (UsernameNotFoundException e) {
            Assertions.assertEquals("Can`t find user", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive UsernameNotFoundException");
    }
}