package com.basakdm.excartest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
/////////////////////////////////////////////////////////////////////////
    //SOME COMMENT////////////////
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
