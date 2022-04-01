package com.solbegsoft.city.library.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CityResponse {

    public final Long id;
    public final String name;
    public final String photo;
}
