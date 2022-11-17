package com.example.trelloapp.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.example.trelloapp.model.User;
import com.example.trelloapp.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            return  withUsername(username)
                    .password(userOptional.get().getPassword())
                    .authorities(userOptional.get().getRoles().stream()
                            .map(r -> r.getRoleName().name()).toArray(String[]::new))
                    .build();
        }
        throw new UsernameNotFoundException("Can`t find user");
    }
}
