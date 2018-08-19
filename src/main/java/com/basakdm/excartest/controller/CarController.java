package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.enum_ent.car_enum.*;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.utils.converters.ConverterCars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    @Autowired
    private CarService carServiceImpl;
    @Autowired
    private CarRepositoryDAO carRepositoryDAO;

    /**
     * Get all cars.
     * @return collection of <CarDTO>.
     */
    @GetMapping("/all")
    public Collection<CarDTO> findAll(){
        log.info("(/car/all), find all cars");
        return carServiceImpl.findAll().stream()
                .map(ConverterCars::mapCar)
                .collect(Collectors.toList());
    }

    /**
     * Find car by id
     * @param carId car unique identifier.
     * @return Optional with car, if car was founded. Empty optional in opposite case.
     */
    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable @Positive Long carId){
        log.info("(/car/{carId}), findCarById()");
        return carServiceImpl.findById(carId)
                .map(ConverterCars::mapCar)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create car.
     * @param carEntity car params for create a new car.
     * @return Created car with id.
     */
    /////////// изменить адрес на create /////////////
    @PostMapping("/createCar")
    public ResponseEntity<?> createCar(@RequestBody CarEntity carEntity){
        carEntity.setIsActivated(false);
        log.info("(/car/createCar), setIsActivated(false)");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterCars.mapCar(carServiceImpl.createCar(carEntity)));
    }

    /**
     * Delete car by id.
     * @param carId car params for delete a car.
     */
    @PostMapping("/delete/{carId}")
    public void delete(@PathVariable @Positive Long carId){
        log.info("(/car/delete/{carId}), delete()");
        carServiceImpl.delete(carId);
    }

    /**
     * Update car by id.
     * @param car car params for update a car.
     */
    @PostMapping ("/update")
    public void update(@RequestBody CarEntity car){
        log.info("(/car/update), updating()");
        carServiceImpl.update(car);
    }

    /**
     * Find cars by (isActivated = false).
     * @return  Collection<CarEntity>.
     */
    @GetMapping("/isActivated/False")
    public Collection<CarEntity> findAllByIsActivatedFalse(){
        log.info("(/car/isActivated/False), findAllByIsActivatedFalse()");
        return carServiceImpl.findAllByIsActivatedFalse();
    }

    /**
     * Find cars by (isActivated = true).
     * @return  Collection<CarEntity>.
     */
    @GetMapping("/isActivated/True")
    public Collection<CarEntity> findAllByIsActivatedTrue(){
        log.info("(/car/isActivated/True), findAllByIsActivatedTrue()");
        return carServiceImpl.findAllByIsActivatedTrue();
    }

    /**
     * Find photo reference by id.
     * @param carId car params to give reference to photo.
     * @return  String - reference to photo.
     */
    @GetMapping(value = "/getPhoto/{carId}")
    public String getPhotoById(@PathVariable @Positive Long carId){
        String photoReference = carServiceImpl.findById(carId).get().getPhoto();
        log.info("(/car/getPhoto/{carId}), photoReference = " + photoReference);
        return photoReference;
    }

    /**
     * Get Location car by id.
     * @param carId car params to give Location.
     * @return  String - Location(coordinates).
     */
    @GetMapping(value = "/getLocation/{carId}")
    public String getLocationById(@PathVariable @Positive Long carId){
        log.info("(/car/getLocation/{carId}), getLocationById()");
        return carServiceImpl.findById(carId).get().getLocation();
    }

    /**
     * Find cars by transmission type.
     * @param transmission car params to give out a list of cars with such a transmission.
     * @return  Collection<CarEntity>.
     */
    @GetMapping(value = "/transmissionType/{transmission}")
    public Collection<CarEntity> getAllByTransmissionType(@PathVariable @Positive Transmission transmission){
        Collection<CarEntity> cars = carServiceImpl.findAllByTransmissionType(transmission);
        log.info("(/car/transmissionType/{transmission}), getAllByTransmissionType()");
        return cars;
    }

    /**
     * Find cars by car Body type.
     * @param carBody params to give out a list of cars with such a Body.
     * @return  Collection<CarEntity>.
     */
    @GetMapping(value = "/carBody/{carBody}")
    public Collection<CarEntity> getAllByCarBody(@PathVariable @Positive CarBody carBody){
        log.info("(/car/carBody/{carBody}), getAllByCarBody()");
        return carServiceImpl.findAllByCarBody(carBody);
    }

    /**
     * Find cars by car Drive gear type.
     * @param driveGear params to give out a list of cars with such a drive gear.
     * @return  Collection<CarEntity>.
     */
    @GetMapping(value = "/driveGear/{driveGear}")
    public Collection<CarEntity> getAllByDriveGear(@PathVariable @Positive DriveGear driveGear){
        log.info("(/car/driveGear/{driveGear}), getAllByDriveGear()");
        return carServiceImpl.findAllByDriveGear(driveGear);
    }

    /**
     * Find cars by car type Engine.
     * @param typeEngine params to give out a list of cars with such a type Engine.
     * @return  Collection<CarEntity>.
     */
    @GetMapping(value = "/typeEngine/{typeEngine}")
    public Collection<CarEntity> getAllByEngineType(@PathVariable @Positive TypeEngine typeEngine){
        log.info("(/car/typeEngine/{typeEngine}), getAllByEngineType()");
        return carServiceImpl.findAllByEngineType(typeEngine);
    }

    /**
     * Find cars by car type Fuel.
     * @param typeFuel params to give out a list of cars with such a type Fuel.
     * @return  Collection<CarEntity>.
     */
    @GetMapping(value = "/typeFuel/{typeFuel}")
    public Collection<CarEntity> getAllByTypeFuel(@PathVariable @Positive TypeFuel typeFuel){
        log.info("(/car/typeFuel/{typeFuel}), getAllByTypeFuel()");
        return carServiceImpl.findAllByFuelType(typeFuel);
    }

    /**
     * Get price car by idCar.
     * @param carId params to give price.
     * @return  Long - price.
     */
    @GetMapping(value = "/getPrice/{carId}")
    public Long getPriceById(@PathVariable @Positive Long carId){
        log.info("(/car/getPrice/{carId}), getPriceById()");
        return carServiceImpl.findById(carId).get().getPrice();
    }
}
