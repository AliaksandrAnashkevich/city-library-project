package com.solbegsoft.city.library.mapper;

import com.solbegsoft.city.library.util.InsertDataCreator;
import com.solbegsoft.city.library.util.TestDataCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CityMapperTest {

    @InjectMocks
    CityMapperImpl cityMapper;

    @Test
    void toDto() {
        // given
        var city = InsertDataCreator.insertCity();
        var extend = TestDataCreator.RESPONSE_CITY;
        // when
        var actual = cityMapper.toDto(city);
        // then
        assertThat(actual.getName()).isEqualTo(extend.getName());
        assertThat(actual.getPhoto()).isEqualTo(extend.getPhoto());
    }

    @Test
    void toEntity() {
        // given
        var city = InsertDataCreator.insertCity();
        var request = TestDataCreator.REQUEST_UPDATE_CITY;

        var extend = InsertDataCreator.insertUpdatedCity();
        // when
        var actual = cityMapper.toEntity(request, city);
        // then
        assertThat(actual.getName()).isEqualTo(extend.getName());
        assertThat(actual.getPhoto()).isEqualTo(extend.getPhoto());
    }
}