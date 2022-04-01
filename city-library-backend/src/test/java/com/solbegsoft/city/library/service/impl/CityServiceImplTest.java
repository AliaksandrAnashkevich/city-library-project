package com.solbegsoft.city.library.service.impl;

import com.solbegsoft.city.library.mapper.CityMapperImpl;
import com.solbegsoft.city.library.model.City;
import com.solbegsoft.city.library.repository.CityRepository;
import com.solbegsoft.city.library.util.InsertDataCreator;
import com.solbegsoft.city.library.util.TestDataCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CityServiceImplTest {


    @Mock
    CityRepository cityRepository;

    @Mock
    CityMapperImpl cityMapper;

    @InjectMocks
    CityServiceImpl cityService;

    @Test
    void findById() {
        // given
        var id = 1L;

        var city = InsertDataCreator.insertCity();
        city.setId(id);
        var extend = TestDataCreator.RESPONSE_CITY;
        // when
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));
        when(cityMapper.toDto(city)).thenReturn(extend);
        // then
        var actual = cityService.findById(id);
        assertThat(actual.getName()).isEqualTo(extend.getName());
        assertThat(actual.getPhoto()).isEqualTo(extend.getPhoto());
    }

    @Test
    void findByPage() {
        var numberPage = 0;
        var size = 20;
        var extend = TestDataCreator.RESPONSE_CITY;

        var city = InsertDataCreator.insertCity();
        var cities = new PageImpl<>(List.of(city));
        var pageable = PageRequest.of(numberPage, size);
        // when
        when(cityRepository.findAll(pageable)).thenReturn(cities);
        when(cityMapper.toDto(city)).thenReturn(extend);
        // then
        var actual = cityService.findByPage(numberPage, size);
        assertTrue(actual.getContent().size() > 0);
    }

    @Test
    void update() {
        // given
        var id = 1L;

        var city = InsertDataCreator.insertCity();
        city.setId(id);

        var updatedCity = InsertDataCreator.insertUpdatedCity();
        updatedCity.setId(id);

        var cityRequest = TestDataCreator.REQUEST_UPDATE_CITY;

        var extend = TestDataCreator.RESPONSE_UPDATE_CITY;
        // when
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));
        when(cityRepository.save(any(City.class))).thenReturn(updatedCity);
        when(cityMapper.toEntity(cityRequest, city)).thenReturn(updatedCity);
        when(cityMapper.toDto(updatedCity)).thenReturn(extend);
        // then
        var actual = cityService.update(id, cityRequest);
        assertThat(actual.getName()).isEqualTo(extend.getName());
        assertThat(actual.getPhoto()).isEqualTo(extend.getPhoto());
    }
}