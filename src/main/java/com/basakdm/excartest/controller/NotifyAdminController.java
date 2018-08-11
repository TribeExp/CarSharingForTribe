package com.basakdm.excartest.controller;

import com.basakdm.excartest.entity.NotifyAdmin;
import com.basakdm.excartest.service.NotifyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/notifyAdmin")
public class NotifyAdminController {

    @Autowired
    private NotifyAdminService notifyAdminService;

    @GetMapping("/all")
    Collection<NotifyAdmin> findAll(){
        return notifyAdminService.findAll();
    }

    /*@GetMapping(value = "/{carId}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable @Positive Long carId){
        return carServiceImpl.findById(carId)
                .map(ConverterCars::mapCar)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/createCar")
    public CarDTO createCar(CarEntity carEntity){
        return ConverterCars.mapCar(carServiceImpl.createCar(carEntity));
    }*/

}
