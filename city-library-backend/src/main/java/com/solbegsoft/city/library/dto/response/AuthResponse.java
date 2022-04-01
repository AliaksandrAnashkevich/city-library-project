package com.solbegsoft.city.library.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponse {

    private final String email;
    private final String token;
}
