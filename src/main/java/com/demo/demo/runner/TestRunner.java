package com.demo.demo.runner;

import com.demo.demo.entity.Role;
import com.demo.demo.entity.User;
import com.demo.demo.service.RoleService;
import com.demo.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestRunner implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    @Override
    public void run(String... args) throws Exception {
//
//        Role firstRole = new Role();
//        firstRole.setName("ADMIN");
//        roleService.save(firstRole);
//
//        Role secondRole = new Role();
//        secondRole.setName("CUSTOMER");
//        roleService.save(secondRole);
//        Role updatedRole = new Role();
//        updatedRole.setName("UPDATED_CUSTOMER");
//        roleService.update(updatedRole,2L);
//
//        User user = new User();
//        user.setUsername("nurely");
//        user.setPassword("pass");
//        user.setRole(roleService.findByName("CUSTOMER"));
//        userService.save(user);

    }
}
