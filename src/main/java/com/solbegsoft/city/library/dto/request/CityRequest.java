package com.solbegsoft.city.library.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CityRequest {

    public final String name;
    public final String photo;
}
