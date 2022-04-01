package com.solbegsoft.city.library.controller;

import com.solbegsoft.city.library.dto.request.AuthRequest;
import com.solbegsoft.city.library.dto.response.AuthResponse;
import com.solbegsoft.city.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return userService.login(authRequest);
    }
}
