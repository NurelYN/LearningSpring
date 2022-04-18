package com.demo.demo.controller;

import com.demo.demo.converter.UserConverter;
import com.demo.demo.dto.user.UserResponse;
import com.demo.demo.dto.user.UserSaveRequest;
import com.demo.demo.entity.User;
import com.demo.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.geom.RectangularShape;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value="/users")
public class UserController {


    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public ResponseEntity<Set<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll().
                stream()
                .map(userConverter::convert)
                .collect(Collectors.toSet()));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserSaveRequest userSaveRequest){
        User user = userConverter.convert(userSaveRequest);
        User savedUser = userService.save(user);
        UserResponse userResponse= userConverter.convert(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="username/{username}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String username){
        userService.delete(username);
        return ResponseEntity.ok().build();
    }
}
