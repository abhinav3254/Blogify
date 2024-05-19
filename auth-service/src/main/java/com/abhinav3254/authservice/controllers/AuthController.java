package com.abhinav3254.authservice.controllers;


import com.abhinav3254.authservice.dto.*;
import com.abhinav3254.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    private AuthResponse<?> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/register")
    private AuthResponse<?> register(@ModelAttribute SignUpDTO sign) {
        return authService.register(sign);
    }

    @PutMapping("/update")
    private AuthResponse<?> updateProfile(@ModelAttribute SignUpDTO sign) {
        return authService.updateProfile(sign);
    }

    // for others profile
    @GetMapping("/profile/{userId}")
    private OtherProfileDTO getProfile(@PathVariable("userId") Long userId) {
        return authService.getProfile(userId);
    }

    // for self profile
    @GetMapping("/profile")
    private ProfileDTO getMyProfile() {
        return authService.getMyProfile();
    }

}
