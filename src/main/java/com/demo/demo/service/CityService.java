package com.demo.demo.service;

import com.demo.demo.entity.City;
import com.demo.demo.exception.DuplicateRecordException;
import com.demo.demo.exception.RecordNotFoundException;
import com.demo.demo.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class CityService {

    private final CityRepository cityRepository;

    public Set<City> findAll(){
        return new HashSet<>(cityRepository.findAll());
    }

    public City save(City city){
        try{
            return cityRepository.save(city);
        }catch(DataIntegrityViolationException ex){
            throw new DuplicateRecordException(String.format("City: %s already exist",city.getName()));
        }
    }

    public City findById(Long id){
        return cityRepository.findById(id)
                .orElseThrow(()->new RecordNotFoundException(String.format("City with id: %s is not found",id)));
    }

    public City findByName(String name){
        return cityRepository.findByName(name)
                .orElseThrow(()->new RecordNotFoundException(String.format("City with name: %s is not found",name)));
    }

    public void delete(Long id){
        cityRepository.deleteById(id);
    }

    public void delete(String name){
        cityRepository.deleteByName(name);
    }

    public City update(City city,Long id){
        City dbCity = findById(id);
        dbCity.setName(city.getName());
        return save(dbCity);
    }
}
