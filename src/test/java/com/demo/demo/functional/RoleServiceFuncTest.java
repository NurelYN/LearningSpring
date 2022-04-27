package com.demo.demo.functional;

import com.demo.demo.entity.Role;
import com.demo.demo.repository.RoleRepository;
import com.demo.demo.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RoleServiceFuncTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void verifySave(){
        Role expectedRole = Role.builder()
                .name("anyName")
                .build();
       Role actualRole = roleService.save(expectedRole);

       assertThat(expectedRole.getName(),is(actualRole.getName()));
       assertThat(1,is(actualRole.getId()));

    }

    @Test
    public void findById(){
        Role role = Role.builder()
                .name("anyName")
                .build();
        Role savedRole = roleRepository.save(role);
       Role foundRoleById = roleService.findById(savedRole.getId());

       assertThat(foundRoleById,is(notNullValue()));
    }
}
