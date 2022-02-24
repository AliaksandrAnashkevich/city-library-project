package com.solbegsoft.city.library.service;

import com.solbegsoft.city.library.dto.request.CityRequest;
import com.solbegsoft.city.library.dto.response.CityResponse;
import org.springframework.data.domain.Page;

public interface CityService {

    CityResponse findById(Long id);

    Page<CityResponse> findByPage(Integer numberPage, Integer size);

    CityResponse update(Long id, CityRequest cityRequest);
}
