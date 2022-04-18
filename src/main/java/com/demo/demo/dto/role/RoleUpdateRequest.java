package com.demo.demo.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateRequest {

    @NotNull
    private Long id;

    @NotNull
    private String name;
}
