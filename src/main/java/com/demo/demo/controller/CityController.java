package com.demo.demo.controller;

import com.demo.demo.converter.CityConverter;
import com.demo.demo.dto.city.CityResponse;
import com.demo.demo.dto.city.CitySaveRequest;
import com.demo.demo.dto.city.CityUpdateRequest;
import com.demo.demo.entity.City;
import com.demo.demo.service.CityService;
import jdk.javadoc.doclet.Reporter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value="cities")
public class CityController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    @GetMapping(value="/{id}")
    public ResponseEntity<CityResponse>findById(@PathVariable Long id){
        City city = cityService.findById(id);
        CityResponse cityResponse = cityConverter.convert(city);
        return ResponseEntity.ok().body(cityResponse);
    }

    @GetMapping
    public ResponseEntity<Set<CityResponse>>findAll(){
        return ResponseEntity.ok().body(cityService.findAll()
                .stream().
                map(cityConverter::convert).
                collect(Collectors.toSet()));
    }

    @PostMapping
    public ResponseEntity<CityResponse>save(@RequestBody @Valid CitySaveRequest citySaveRequest) {
        City city = cityConverter.convert(citySaveRequest);
        City savedCity = cityService.save(city);
        CityResponse responseCity = cityConverter.convert(savedCity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCity);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<HttpStatus>delete(@PathVariable  Long id){
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<CityResponse>update(@RequestBody CityUpdateRequest cityUpdateRequest,@PathVariable Long id){
      City city = cityConverter.convert(cityUpdateRequest);
      City updatedCity = cityService.update(city,id);
      return ResponseEntity.ok().body(cityConverter.convert(updatedCity));
    }
}
