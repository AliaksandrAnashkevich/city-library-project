package com.solbegsoft.city.library.service.impl;

import com.solbegsoft.city.library.dto.request.CityRequest;
import com.solbegsoft.city.library.dto.response.CityResponse;
import com.solbegsoft.city.library.exception.CityNotFoundException;
import com.solbegsoft.city.library.mapper.CityMapper;
import com.solbegsoft.city.library.model.City;
import com.solbegsoft.city.library.repository.CityRepository;
import com.solbegsoft.city.library.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityResponse findById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));

        return cityMapper.toDto(city);
    }

    @Override
    public Page<CityResponse> findByPage(Integer numberPage, Integer size) {
        Pageable pageable = PageRequest.of(numberPage, size);
        Page<City> cities = cityRepository.findAll(pageable);

        return cities.map(cityMapper::toDto);
    }

    @Override
    public CityResponse update(Long id, CityRequest cityRequest) {
        City updatedCity = cityRepository.findById(id)
                .map(city -> cityMapper.toEntity(cityRequest, city))
                .orElseThrow(() -> new CityNotFoundException(id));

        updatedCity = cityRepository.save(updatedCity);

        return cityMapper.toDto(updatedCity);
    }
}