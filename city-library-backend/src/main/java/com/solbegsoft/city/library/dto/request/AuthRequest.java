package com.solbegsoft.city.library.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthRequest {

    private final String email;
    private final String password;
}
