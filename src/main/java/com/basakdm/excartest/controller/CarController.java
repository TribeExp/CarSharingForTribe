package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carServiceImpl;

    @GetMapping("/all")
    public Collection<CarDTO> findAll(){
        return carServiceImpl.findAll();
    }

    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable @Positive Long carId){
        return carServiceImpl.getCarById(carId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/createCar")
    public CarDTO createCar(CarDTO carDto){
        return carServiceImpl.createCar(carDto);
    }
}
