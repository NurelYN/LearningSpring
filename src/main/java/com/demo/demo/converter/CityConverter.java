package com.demo.demo.converter;

import com.demo.demo.dto.city.CityResponse;
import com.demo.demo.dto.city.CitySaveRequest;
import com.demo.demo.dto.city.CityUpdateRequest;
import com.demo.demo.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    public CityResponse convert(City city){
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

    public City convert(CitySaveRequest citySaveRequest){
        return City.builder()
                .name(citySaveRequest.getName())
                .build();
    }

    public City convert(CityUpdateRequest cityUpdateRequest){
        return City.builder()
                .name(cityUpdateRequest.getName())
                .build();
    }
}
