package com.solbegsoft.city.library.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String email;
    private String token;
}
