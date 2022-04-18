package com.demo.demo.dto.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CityResponse {

    private Long id;
    private String name;
}
