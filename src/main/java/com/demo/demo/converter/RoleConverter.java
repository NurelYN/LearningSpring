package com.demo.demo.converter;

import com.demo.demo.dto.role.RoleSaveRequest;
import com.demo.demo.dto.role.RoleResponse;
import com.demo.demo.dto.role.RoleUpdateRequest;
import com.demo.demo.entity.Role;
import org.springframework.stereotype.Component;


@Component
public class RoleConverter {

    public static RoleResponse convert(Role role){
        return RoleResponse.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .build();
    }

    public Role convert(RoleSaveRequest roleSaveRequest){
        return Role.builder()
                .name(roleSaveRequest.getName())
                .build();
    }

    public Role convert(RoleUpdateRequest roleUpdateRequest){
        return Role.builder()
                .id(roleUpdateRequest.getId())
                .name(roleUpdateRequest.getName())
                .build();
    }

}
