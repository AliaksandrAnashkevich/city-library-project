package com.solbegsoft.city.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.city.library.dto.response.CityResponse;
import com.solbegsoft.city.library.model.City;
import com.solbegsoft.city.library.repository.CityRepository;
import com.solbegsoft.city.library.util.InsertDataCreator;
import com.solbegsoft.city.library.util.TestDataCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private City actualCity;

    @BeforeEach
    void insertTestData() {
        var city = InsertDataCreator.insertCity();
        cityRepository.save(city);
        actualCity = city;
    }

    @AfterEach
    void deleteAll() {
        cityRepository.deleteAll();
    }

    @Test
    void getById() throws Exception {
        // given
        var url = "/city/{id}";
        // when
        var response = mockMvc.perform(get(url, actualCity.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // then
        var extend = objectMapper.readValue(response, CityResponse.class);
        assertThat(actualCity.getName()).isEqualTo(extend.getName());
        assertThat(actualCity.getPhoto()).isEqualTo(extend.getPhoto());
    }

    @Test
    void getByPage() throws Exception {
        // given
        var url = "/city?page&size";
        // when, then
        mockMvc.perform(get(url, actualCity.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content",hasSize(1)));
    }

    @WithMockUser(username = "test@example@com", roles = "ALLOW_EDIT", password = "Aa12356")
    @Test
    void update() throws Exception {
        // given
        var url = "/city/{id}";
        var actualDto = TestDataCreator.REQUEST_UPDATE_CITY;
        var inputJson = objectMapper.writeValueAsString(actualDto);
        var request = put(url, actualCity.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson);
        // when
        var response = mockMvc.perform(request
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // then
        var extend = objectMapper.readValue(response, CityResponse.class);
        assertThat(actualDto.getName()).isEqualTo(extend.getName());
        assertThat(actualDto.getPhoto()).isEqualTo(extend.getPhoto());
    }
}