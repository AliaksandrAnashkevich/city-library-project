package com.solbegsoft.city.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.city.library.dto.response.AuthResponse;
import com.solbegsoft.city.library.exception.InvalidAuthRequestDataException;
import com.solbegsoft.city.library.repository.RoleRepository;
import com.solbegsoft.city.library.repository.UserRepository;
import com.solbegsoft.city.library.util.InsertDataCreator;
import com.solbegsoft.city.library.util.TestDataCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void insertTestData() {
        var role = InsertDataCreator.insertRoleAllowSee();
        roleRepository.save(role);

        var user = InsertDataCreator.insertUser(passwordEncoder, Set.of(role));
        userRepository.save(user);
    }

    @AfterEach
    void deleteAll() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void loginSuccess() throws Exception {
        // given
        var actual = TestDataCreator.REQUEST_VALID_LOGIN_TEST_USER;
        var requestBody = objectMapper.writeValueAsString(actual);
        var url = "/auth/login";
        // when
        String response = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // then
        AuthResponse extend = objectMapper.readValue(response, AuthResponse.class);
        assertThat(actual.getEmail()).isEqualTo(extend.getEmail());
    }

    @Test
    void loginThrowInvalidAuthRequestDataException() throws Exception {
        // given
        var actual = TestDataCreator.REQUEST_INVALID_LOGIN_TEST_USER;
        var requestBody = objectMapper.writeValueAsString(actual);
        var url = "/auth/login";
        // when, then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(result -> assertTrue(isCondition(result)));
    }

    private boolean isCondition(MvcResult result) {
        return result.getResolvedException() instanceof InvalidAuthRequestDataException;
    }
}