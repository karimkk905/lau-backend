
package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepository.existsById(user.getUsername())) {
            return "Username already exists!";
        }
        userRepository.save(user);
        return "Signup successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userRepository.findById(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(u -> "Login successful!")
                .orElse("Invalid username or password!");
    }
}
