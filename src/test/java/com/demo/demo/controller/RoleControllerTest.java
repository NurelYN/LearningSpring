package com.demo.demo.controller;

import com.demo.demo.converter.RoleConverter;
import com.demo.demo.dto.role.RoleResponse;
import com.demo.demo.dto.role.RoleSaveRequest;
import com.demo.demo.dto.role.RoleUpdateRequest;
import com.demo.demo.entity.Role;
import com.demo.demo.service.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AllArgsConstructor
@AutoConfigureMockMvc
@WebMvcTest(value = RoleController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class RoleControllerTest {

    @MockBean
    private final RoleService roleService;

    @MockBean
    private final RoleConverter roleConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save() throws Exception {
        RoleSaveRequest roleSaveRequest = RoleSaveRequest.builder()
                .name("anyName")
                .build();

        String roleSaveJSON = objectMapper.writeValueAsString(roleSaveRequest);

        when(roleConverter.convert(any(RoleSaveRequest.class)))
                .thenReturn(Role.builder().build());
        when(roleService.save(any(Role.class)))
                .thenReturn(Role.builder().build());
        when(roleConverter.convert(any(Role.class)))
                .thenReturn(RoleResponse.builder()
                        .id(1L)
                        .name("anyName")
                        .build());
        mockMvc.perform(post("/roles")
                .content(roleSaveJSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.id",is(1)))
                .andExpect((ResultMatcher) jsonPath("$.name",is("anyName")));

    }

    @Test
    public void verifyDelete () throws Exception {

        mockMvc.perform(delete("roles/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void verifyDeleteByName() throws Exception {

        mockMvc.perform(delete("/roles/name/Georgi"))
                .andExpect(status().isOk());
    }

    @Test
    public void verifyUpdate() throws Exception {
        RoleUpdateRequest roleUpdateRequest = RoleUpdateRequest.builder()
                .id(1L)
                .name("anyName")
                .build();
       String roleUpdateJSON = objectMapper.writeValueAsString(roleUpdateRequest);
       when(roleConverter.convert(any(RoleUpdateRequest.class)))
                       .thenReturn(Role.builder()
                               .id(1L)
                               .build());
       when(roleService.update(any(Role.class),any(Long.class)))
               .thenReturn(Role.builder()
                       .build());
        when(roleConverter.convert(any(Role.class)))
                .thenReturn(RoleResponse.builder()
                        .id(1L)
                        .name("updatedName")
                        .build());
        mockMvc.perform(put("/roles").
                content(roleUpdateJSON).
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.id",is(1)))
                .andExpect((ResultMatcher) jsonPath("$.name",is("updatedName")));
    }
}