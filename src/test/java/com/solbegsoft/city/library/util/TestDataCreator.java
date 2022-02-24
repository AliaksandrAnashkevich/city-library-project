package com.solbegsoft.city.library.util;

import com.solbegsoft.city.library.dto.request.AuthRequest;
import com.solbegsoft.city.library.dto.request.CityRequest;
import com.solbegsoft.city.library.dto.response.CityResponse;

public class TestDataCreator {

    public static final String CITY_NAME = "Lida";
    public static final String CITY_PHOTO = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/Belarus_Lida_Castle_%28256497035%29.jpeg/274px-Belarus_Lida_Castle_%28256497035%29.jpeg";
    public static final String UPDATE_CITY_NAME = "Minsk";
    public static final String UPDATE_CITY_PHOTO = "https://upload.wikimedia.org/wikipedia/ru/thumb/0/0e/BelarusNationalLibrary.JPG/290px-BelarusNationalLibrary.JPG";
    public static final String VALID_EMAIL = "valid@example.com";
    public static final String INVALID_EMAIL = "invalid@example.com";
    public static final String PASSWORD = "Aa123456";

    public static AuthRequest REQUEST_VALID_LOGIN_TEST_USER = AuthRequest.builder()
            .email(VALID_EMAIL)
            .password(PASSWORD)
            .build();

    public static AuthRequest REQUEST_INVALID_LOGIN_TEST_USER = AuthRequest.builder()
            .email(INVALID_EMAIL)
            .password(PASSWORD)
            .build();

    public static CityRequest REQUEST_UPDATE_CITY = CityRequest.builder()
            .name(UPDATE_CITY_NAME)
            .photo(UPDATE_CITY_PHOTO)
            .build();

    public static CityResponse RESPONSE_CITY = CityResponse.builder()
            .id(1L)
            .name(CITY_NAME)
            .photo(CITY_PHOTO)
            .build();

    public static CityResponse RESPONSE_UPDATE_CITY = CityResponse.builder()
            .id(1L)
            .name(UPDATE_CITY_NAME)
            .photo(UPDATE_CITY_PHOTO)
            .build();
}
