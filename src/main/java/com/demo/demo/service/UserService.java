package com.demo.demo.service;

import com.demo.demo.entity.Role;
import com.demo.demo.entity.User;
import com.demo.demo.exception.DuplicateRecordException;
import com.demo.demo.repository.RoleRepository;
import com.demo.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user){
        try{
            Role role = roleService.findByName("CUSTOMER");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(role);
            return userRepository.save(user);
        } catch(DataIntegrityViolationException ex){
            throw new DuplicateRecordException(String.format("Username: %s already exist",user.getUsername()));
        }
    }
}
