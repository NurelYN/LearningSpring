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

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
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

    public Set<User> findAll(){
        return new HashSet<>(userRepository.findAll());
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public void delete(String username){
        userRepository.deleteByUsername(username);
    }
}
