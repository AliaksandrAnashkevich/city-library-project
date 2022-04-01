package com.solbegsoft.city.library.exception;

public class CityNotFoundException extends RuntimeException {

    private static final String INVALID_CITY_MESSAGE_ERROR = "City with id=[%s] don't exist";

    public CityNotFoundException(Long id) {
        super(String.format(INVALID_CITY_MESSAGE_ERROR, id));
    }
}
