package com.demo.demo.repository;

import com.demo.demo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    void deleteByName(String name);

    Optional<City>findByName(String name);
}
