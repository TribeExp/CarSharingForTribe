package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.UsersEntity;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.service.CarServiceImpl;
import com.basakdm.excartest.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carServiceImpl;

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


    @GetMapping("/all")
    public List<CarEntity> findAll(){
        return carServiceImpl.findAll();
    }


}
