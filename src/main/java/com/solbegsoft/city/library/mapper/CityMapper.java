package com.solbegsoft.city.library.mapper;

import com.solbegsoft.city.library.dto.request.CityRequest;
import com.solbegsoft.city.library.dto.response.CityResponse;
import com.solbegsoft.city.library.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityResponse toDto(City city);

    @Mapping(target = "id", ignore = true)
    City toEntity(CityRequest cityRequest, @MappingTarget City city);
}
