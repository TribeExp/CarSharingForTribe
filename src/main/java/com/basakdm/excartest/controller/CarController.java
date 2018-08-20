package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.enum_ent.car_enum.*;
import com.basakdm.excartest.request_models.car_models.CarIdAndFlag;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.utils.converters.ConverterCars;
import com.basakdm.excartest.utils.specificationCAR.CarSpecificationsBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    @Autowired
    private CarService carServiceImpl;
    @Autowired
    private CarRepositoryDAO crd;

    @PostMapping("/dall")
    ResponseEntity delall(){
        crd.deleteAll();
        return ResponseEntity.ok().build();
    }

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
     * @return {@link ResponseEntity} car.
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
     * @return {@link ResponseEntity} created car with id.
     */
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
     * @return {@link ResponseEntity}
     */
   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{carId}")
    public ResponseEntity delete(@PathVariable @Positive Long carId){
        log.info("(/car/delete/{carId}), delete()");
        carServiceImpl.delete(carId);
        return ResponseEntity.ok().build();
    }

    /**
     * Update car by id.
     * @param car car params for update a car.
     * @return {@link ResponseEntity}
     */
    @PostMapping ("/update/{carId}")
    public ResponseEntity update(@RequestBody CarEntity car, @PathVariable @Positive Long carId){
        log.info("(/car/update), updating()");
        car.setId(carId);
        carServiceImpl.update(car);
        return ResponseEntity.ok().build();
    }

    /**
     * Find cars by (isActivated = false).
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @GetMapping("/isActivated/False")
    public ResponseEntity<Collection<CarEntity>> findAllByIsActivatedFalse(){
        log.info("(/car/isActivated/False), findAllByIsActivatedFalse()");
        return ResponseEntity.ok(carServiceImpl.findAllByIsActivatedFalse());
    }

    /**
     * Find cars by (isActivated = true).
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @GetMapping("/isActivated/True")
    public ResponseEntity<Collection<CarEntity>> findAllByIsActivatedTrue(){
        log.info("(/car/isActivated/True), findAllByIsActivatedTrue()");
        return ResponseEntity.ok(carServiceImpl.findAllByIsActivatedTrue());
    }

    /**
     * Find photo reference by id.
     * @param carId car params to give reference to photo.
     * @return {@link ResponseEntity} reference to photo.
     */
    @GetMapping(value = "/getPhoto/{carId}")
    public ResponseEntity getPhotoById(@PathVariable @Positive Long carId){
        log.info("(/car/getPhoto/{carId}), getPhotoById()");
        return carServiceImpl.findById(carId)
                .map(car -> ResponseEntity.ok(car.getPhoto()))
                .orElseGet(() -> ResponseEntity.ok().build());
    }

    /**
     * Get Location car by id.
     * @param carId car params to give Location.
     * @return {@link ResponseEntity} location(coordinates).
     */
    @GetMapping(value = "/getLocation/{carId}")
    public ResponseEntity getLocationById(@PathVariable @Positive Long carId){
        log.info("(/car/getLocation/{carId}), getLocationById()");
        return carServiceImpl.findById(carId)
                .map(car -> ResponseEntity.ok(car.getLocation()))
                .orElseGet(() -> ResponseEntity.ok().build());
    }

    /**
     * Find cars by transmission type.
     * @param transmission car params to give out a list of cars with such a transmission.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @GetMapping(value = "/transmissionType/{transmission}")
    public ResponseEntity<Collection<CarEntity>> getAllByTransmissionType(@PathVariable @Positive Transmission transmission){
        log.info("(/car/transmissionType/{transmission}), getAllByTransmissionType()");
        return ResponseEntity.ok(carServiceImpl.findAllByTransmissionType(transmission));
    }

    /**
     * Find cars by car Body type.
     * @param carBody params to give out a list of cars with such a Body.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @GetMapping(value = "/carBody/{carBody}")
    public ResponseEntity<Collection<CarEntity>> getAllByCarBody(@PathVariable @Positive CarBody carBody){
        log.info("(/car/carBody/{carBody}), getAllByCarBody()");
        return ResponseEntity.ok(carServiceImpl.findAllByCarBody(carBody));
    }

    /**
     * Find cars by car Drive gear type.
     * @param driveGear params to give out a list of cars with such a drive gear.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @GetMapping(value = "/driveGear/{driveGear}")
    public ResponseEntity<Collection<CarEntity>> getAllByDriveGear(@PathVariable @Positive DriveGear driveGear){
        log.info("(/car/driveGear/{driveGear}), getAllByDriveGear()");
        return ResponseEntity.ok(carServiceImpl.findAllByDriveGear(driveGear));
    }

    /**
     * Find cars by car type Engine.
     * @param typeEngine params to give out a list of cars with such a type Engine.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @GetMapping(value = "/typeEngine/{typeEngine}")
    public ResponseEntity<Collection<CarEntity>> getAllByEngineType(@PathVariable @Positive TypeEngine typeEngine){
        log.info("(/car/typeEngine/{typeEngine}), getAllByEngineType()");
        return ResponseEntity.ok(carServiceImpl.findAllByEngineType(typeEngine));
    }

    /**
     * Find cars by car type Fuel.
     * @param typeFuel params to give out a list of cars with such a type Fuel.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @GetMapping(value = "/typeFuel/{typeFuel}")
    public ResponseEntity<Collection<CarEntity>> getAllByTypeFuel(@PathVariable @Positive TypeFuel typeFuel){
        log.info("(/car/typeFuel/{typeFuel}), getAllByTypeFuel()");
        return ResponseEntity.ok(carServiceImpl.findAllByFuelType(typeFuel));
    }

    /**
     * Get price car by idCar.
     * @param carId params to give price.
     * @return  {@link ResponseEntity} price.
     */
    @GetMapping(value = "/getPrice/{carId}")
    public ResponseEntity getPriceById(@PathVariable @Positive Long carId){
        log.info("(/car/getPrice/{carId}), getPriceById()");
        return carServiceImpl.findById(carId)
                .map(car -> ResponseEntity.ok(car.getPrice()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Set availability of car
     * @param carIdAndFlag {@link CarIdAndFlag}
     * @return {@link ResponseEntity}
     */
    @PostMapping("/setAvailability")
    public ResponseEntity setAvailability(CarIdAndFlag carIdAndFlag){
        try{
            carServiceImpl.setIsFree(carIdAndFlag.isFlag(), carIdAndFlag.getCarId());
        } catch (Exception e){

        }
        log.info("(/car/setAvailability), setAvailability() - " + carIdAndFlag.isFlag());
        return ResponseEntity.ok().build();
    }

    /**
     * Set activation status
     * @param carIdAndFlag {@link CarIdAndFlag}
     * @return {@link ResponseEntity}
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/activateCar")
    public ResponseEntity setIsActivated(CarIdAndFlag carIdAndFlag){
        try{
            carServiceImpl.activateCar(carIdAndFlag.isFlag(), carIdAndFlag.getCarId());
        } catch (Exception e){

        }

        log.info("(/car/activateCar), setIsActivated() - " + carIdAndFlag.isFlag());
        return ResponseEntity.ok().build();
    }

    /**
     * Looking for cars with specified parameters
     * @param search settings find for findAdd
     * @return {@link ResponseEntity} if found body contains {@link List<CarEntity>}
     */
    @GetMapping("/search")
    public ResponseEntity search(@RequestParam(value = "search") String search) {
        CarSpecificationsBuilder builder = new CarSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<CarEntity> spec = builder.build();
        log.info("(/car/search), search()");
        return ResponseEntity.ok(carServiceImpl.findAllBySpecification(spec));
    }
}
