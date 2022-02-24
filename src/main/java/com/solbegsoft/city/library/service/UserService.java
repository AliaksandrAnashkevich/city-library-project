package com.solbegsoft.city.library.service;

import com.solbegsoft.city.library.dto.request.AuthRequest;
import com.solbegsoft.city.library.dto.response.AuthResponse;

public interface UserService {

    AuthResponse login(AuthRequest authRequest);
}
