package com.student.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.student.demo.dto.RegisterRequestDTO;
import com.student.demo.entity.User;
import com.student.demo.repository.UserRepository;

import main.java.com.student.demo.exception.UsernameAlreadyTakenException;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService (UserRepository repository, PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override 
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }

    public String register (RegisterRequestDTO dto) {

        if (repository.existsByUsername(dto.getUsername())) {

            throw new UsernameAlreadyTakenException ("Username already taken!");

        }

        User user = new User(
            dto.getUsername(),
            passwordEncoder.encode(dto.getPassword()),
            "USER"

        );

        repository.save(user);
        return "User registered successfully!";


    }
    
}
