package com.student.demo.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.dto.AuthRequestDTO;
import com.student.demo.dto.AuthResponseDTO;
import com.student.demo.dto.RegisterRequestDTO;
import com.student.demo.security.JwtUtil;
import com.student.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController (JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder) {

        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@Valid @RequestBody RegisterRequestDTO registerDTO) {

        String result = userService.register(registerDTO);
        return ResponseEntity.status(201).body(result);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody AuthRequestDTO requestDTO) {

        UserDetails userDetails;

        try {
            userDetails = userService.loadUserByUsername(requestDTO.getUsername());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401)
                    .body(new AuthResponseDTO("Invalid credentials"));
        }

        if (!passwordEncoder.matches(requestDTO.getPassword(), userDetails.getPassword())) {

            return ResponseEntity.status(401)
                    .body(new AuthResponseDTO("Invalid credentials"));
            
        }

        String token = jwtUtil.generateToken(requestDTO.getUsername());
        return ResponseEntity.ok(new AuthResponseDTO(token));

    }
    
}
