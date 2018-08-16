package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.OrderEntity;
import com.basakdm.excartest.enum_ent.car_enum.*;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.utils.ConverterCars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carServiceImpl;
    @Autowired
    private CarRepositoryDAO carRepositoryDAO;

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

    @PostMapping("/createCar")
    public ResponseEntity<?> createCar(@RequestBody CarEntity carEntity){
        carEntity.setIsActivated(false);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterCars.mapCar(carServiceImpl.createCar(carEntity)));
    }

    @DeleteMapping("/delete/{carId}")
    public void delete(@PathVariable @Positive Long carId){
        carServiceImpl.delete(carId);
    }

    @PostMapping ("/update")
    public void update(@RequestBody CarEntity car){
        carServiceImpl.update(car);
    }


    @GetMapping("/isActivated/False")
    public Collection<CarEntity> findAllByIsActivatedFalse(){
        return carServiceImpl.findAllByIsActivatedFalse();
    }
    @GetMapping("/isActivated/True")
    public Collection<CarEntity> findAllByIsActivatedTrue(){
        return carServiceImpl.findAllByIsActivatedTrue();
    }


    @GetMapping(value = "/getPhoto/{carId}")
    public String getPhotoById(@PathVariable @Positive Long carId){
        String photoReference = carServiceImpl.findById(carId).get().getPhoto();
        return photoReference;
    }
    @GetMapping(value = "/getLocation/{carId}")
    public String getLocationById(@PathVariable @Positive Long carId){
        return carServiceImpl.findById(carId).get().getLocation();
    }


    @GetMapping(value = "/transmissionType/{transmission}")
    public Collection<CarEntity> getAllByTransmissionType(@PathVariable @Positive Transmission transmission){
        Collection<CarEntity> cars = carServiceImpl.findAllByTransmissionType(transmission);
        return cars;
    }
    @GetMapping(value = "/carBody/{carBody}")
    public Collection<CarEntity> getAllByCarBody(@PathVariable @Positive CarBody carBody){
        return carServiceImpl.findAllByCarBody(carBody);
    }
    @GetMapping(value = "/driveGear/{driveGear}")
    public Collection<CarEntity> getAllByDriveGear(@PathVariable @Positive DriveGear driveGear){
        return carServiceImpl.findAllByDriveGear(driveGear);
    }
    @GetMapping(value = "/typeEngine/{typeEngine}")
    public Collection<CarEntity> getAllByEngineType(@PathVariable @Positive TypeEngine typeEngine){
        return carServiceImpl.findAllByEngineType(typeEngine);
    }
    @GetMapping(value = "/typeFuel/{typeFuel}")
    public Collection<CarEntity> getAllByTypeFuel(@PathVariable @Positive TypeFuel typeFuel){
        return carServiceImpl.findAllByFuelType(typeFuel);
    }


    @GetMapping(value = "/getPrice/{carId}")
    public Long getPriceById(@PathVariable @Positive Long carId){
        return carServiceImpl.findById(carId).get().getPrice();
    }
    /*@PostMapping ("/setPrice/{carId}/{price}")
    public void setPrice(@RequestBody @PathVariable @Positive Long carId, @PathVariable @Positive Long price){

        Optional<CarEntity> optionalCarEntity = carServiceImpl.findById(carId);
        CarEntity carEntity = optionalCarEntity.get();
        carEntity.setPrice(price);

        carRepositoryDAO.saveAndFlush(carEntity);
    }*/

}
