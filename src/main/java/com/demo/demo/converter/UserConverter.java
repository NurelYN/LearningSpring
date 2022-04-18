package com.demo.demo.converter;

import com.demo.demo.dto.city.CityResponse;
import com.demo.demo.dto.role.RoleResponse;
import com.demo.demo.dto.user.UserResponse;
import com.demo.demo.dto.user.UserSaveRequest;
import com.demo.demo.entity.City;
import com.demo.demo.entity.User;
import com.demo.demo.service.CityService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserConverter {

    private final CityService cityService;

    public User convert(UserSaveRequest userSaveRequest){

        City findCity = cityService.findByName(userSaveRequest.getCityName());
        return User.builder()
                .username(userSaveRequest.getUsername())
                .password(userSaveRequest.getPassword())
                .city(findCity)
                .build();
    }

    public UserResponse convert(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roleResponse(RoleResponse.builder() // it can be done with converter (roleConverter.convert(user.getRole());
                        .id(user.getRole().getId())
                        .name(user.getRole().getName())
                        .build())
                .cityResponse(CityResponse.builder()
                        .id(user.getCity().getId())
                        .name(user.getCity().getName())
                        .build())
                .build();
    }
}
