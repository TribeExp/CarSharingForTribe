package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.NotifyAdminRepositoryDAO;
import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.NotifyAdmin;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.utils.ConverterCars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carServiceImpl;
    @Autowired
    private NotifyAdminRepositoryDAO notifyAdminController;

    @GetMapping("/all")
    public Collection<CarDTO> findAll(){
        return carServiceImpl.findAll().stream()
                .map(ConverterCars::mapCar)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable @Positive Long carId){
        return carServiceImpl.findById(carId)
                .map(ConverterCars::mapCar)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/createCar")
    public CarDTO createCar(CarEntity carEntity){
        return ConverterCars.mapCar(carServiceImpl.createCar(carEntity));
    }

    @GetMapping(name = "not")
    List<NotifyAdmin> not(){
        return notifyAdminController.findAll();
    }
}
