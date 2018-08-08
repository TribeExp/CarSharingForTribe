package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.dao.UserRepository;
import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.dto.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
/////////////////////////////////////////////////////////////////////////
    /*@GetMapping(value = "/{carId}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable @Positive Long carId){
        return carService.getCarById(carId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/allCar}")
    public Collection<CarDTO> findAll(){
        return carService.findAll();
    }

    @GetMapping(value = "/createCar")
    public CarDTO createCar(CarDTO carDto){
        return carService.createCar(carDto);
    }

*/


}
