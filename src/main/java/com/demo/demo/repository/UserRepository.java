package com.demo.demo.repository;

import com.demo.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    void deleteByUsername(String username);
}
