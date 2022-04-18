package com.demo.demo.dto.user;

import com.demo.demo.dto.city.CitySaveRequest;
import com.demo.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String cityName; //  private CitySaveRequest citySaveRequest

}
