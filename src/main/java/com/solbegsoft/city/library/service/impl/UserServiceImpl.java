package com.solbegsoft.city.library.service.impl;

import com.solbegsoft.city.library.dto.request.AuthRequest;
import com.solbegsoft.city.library.dto.responce.AuthResponse;
import com.solbegsoft.city.library.exception.InvalidAuthRequestDataException;
import com.solbegsoft.city.library.security.JwtTokenProvider;
import com.solbegsoft.city.library.security.JwtUser;
import com.solbegsoft.city.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));
            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            String token = jwtTokenProvider.createToken(email, jwtUser.getRoles());
            return AuthResponse.builder()
                    .email(email)
                    .token(token)
                    .build();
        } catch (AuthenticationException ex) {
            throw new InvalidAuthRequestDataException();
        }
    }
}
