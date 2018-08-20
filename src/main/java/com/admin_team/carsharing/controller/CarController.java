package com.admin_team.carsharing.controller;

import com.admin_team.carsharing.enum_ent.car_enum.*;
import com.admin_team.carsharing.request_models.car_models.CarIdAndFlag;
import com.admin_team.carsharing.utils.specificationCAR.CarSpecificationsBuilder;
import com.admin_team.carsharing.dao.CarRepositoryDAO;
import com.admin_team.carsharing.dto.CarDTO;
import com.admin_team.carsharing.entity.CarEntity;
import com.admin_team.carsharing.service.CarService;
import com.admin_team.carsharing.utils.converters.ConverterCars;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(value = "Controller for interaction with the methods car", description = "The operations that can be performed with the car table are in this controller")
@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    @Autowired
    private CarService carServiceImpl;
    @Autowired
    private CarRepositoryDAO crd;

    /**
     * Get all cars.
     * @return collection of <CarDTO>.
     */
    @ApiOperation(value = "Outputting the entire list of car in the car table.", notes = "")
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
    @ApiOperation(value = "Find car by id.", notes = "")
    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable @Positive @ApiParam("id car to find") Long carId){
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
    @ApiOperation(value = "The method creates a new row in the car table.", notes = "")
    @PostMapping("/createCar")
    public ResponseEntity<?> createCar(@RequestBody @ApiParam("Model for create car") CarEntity carEntity){
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
    @ApiOperation(value = "Removing a car from the database, by id.", notes = "")
    @PostMapping("/delete/{carId}")
    public ResponseEntity delete(@PathVariable @Positive @ApiParam("id car to delete") Long carId){
        log.info("(/car/delete/{carId}), delete()");
        carServiceImpl.delete(carId);
        return ResponseEntity.ok().build();
    }

    /**
     * Update car by id.
     * @param car car params for update a car.
     * @return {@link ResponseEntity}
     */
    @ApiOperation(value = "Update a car from the database, by model.", notes = "")
    @PostMapping ("/update/{carId}")
    public ResponseEntity update(@RequestBody @ApiParam("car model") CarEntity car, @PathVariable @Positive @ApiParam("id car") Long carId){
        log.info("(/car/update), updating()");
        car.setId(carId);
        carServiceImpl.update(car);
        return ResponseEntity.ok().build();
    }

    /**
     * Find cars by (isActivated = false).
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @ApiOperation(value = "Find cars by (isActivated = false).", notes = "")
    @GetMapping("/isActivated/False")
    public ResponseEntity<Collection<CarEntity>> findAllByIsActivatedFalse(){
        log.info("(/car/isActivated/False), findAllByIsActivatedFalse()");
        return ResponseEntity.ok(carServiceImpl.findAllByIsActivatedFalse());
    }

    /**
     * Find cars by (isActivated = true).
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @ApiOperation(value = "Find cars by (isActivated = true).", notes = "")
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
    @ApiOperation(value = "Find photo reference by id.", notes = "")
    @GetMapping(value = "/getPhoto/{carId}")
    public ResponseEntity getPhotoById(@PathVariable @Positive @ApiParam("id car to find photo") Long carId){
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
    @ApiOperation(value = "Get Location car by id.", notes = "")
    @GetMapping(value = "/getLocation/{carId}")
    public ResponseEntity getLocationById(@PathVariable @Positive @ApiParam("id car to find location") Long carId){
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
    @ApiOperation(value = "Find cars by transmission type.", notes = "")
    @GetMapping(value = "/transmissionType/{transmission}")
    public ResponseEntity<Collection<CarEntity>> getAllByTransmissionType(@PathVariable @Positive @ApiParam("transmission car params to give out a list of cars with such a transmission.") Transmission transmission){
        log.info("(/car/transmissionType/{transmission}), getAllByTransmissionType()");
        return ResponseEntity.ok(carServiceImpl.findAllByTransmissionType(transmission));
    }

    /**
     * Find cars by car Body type.
     * @param carBody params to give out a list of cars with such a Body.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @ApiOperation(value = "Find cars by car Body type.", notes = "")
    @GetMapping(value = "/carBody/{carBody}")
    public ResponseEntity<Collection<CarEntity>> getAllByCarBody(@PathVariable @Positive @ApiParam("carBody params to give out a list of cars with such a Body.") CarBody carBody){
        log.info("(/car/carBody/{carBody}), getAllByCarBody()");
        return ResponseEntity.ok(carServiceImpl.findAllByCarBody(carBody));
    }

    /**
     * Find cars by car Drive gear type.
     * @param driveGear params to give out a list of cars with such a drive gear.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @ApiOperation(value = "Find cars by car Drive gear type.", notes = "")
    @GetMapping(value = "/driveGear/{driveGear}")
    public ResponseEntity<Collection<CarEntity>> getAllByDriveGear(@PathVariable @Positive @ApiParam("driveGear params to give out a list of cars with such a drive gear.") DriveGear driveGear){
        log.info("(/car/driveGear/{driveGear}), getAllByDriveGear()");
        return ResponseEntity.ok(carServiceImpl.findAllByDriveGear(driveGear));
    }

    /**
     * Find cars by car type Engine.
     * @param typeEngine params to give out a list of cars with such a type Engine.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @ApiOperation(value = "Find cars by car type Engine.", notes = "")
    @GetMapping(value = "/typeEngine/{typeEngine}")
    public ResponseEntity<Collection<CarEntity>> getAllByEngineType(@PathVariable @Positive @ApiParam("typeEngine params to give out a list of cars with such a type Engine.") TypeEngine typeEngine){
        log.info("(/car/typeEngine/{typeEngine}), getAllByEngineType()");
        return ResponseEntity.ok(carServiceImpl.findAllByEngineType(typeEngine));
    }

    /**
     * Find cars by car type Fuel.
     * @param typeFuel params to give out a list of cars with such a type Fuel.
     * @return  {@link ResponseEntity<Collection<CarEntity>>}.
     */
    @ApiOperation(value = "Find cars by car type Fuel.", notes = "")
    @GetMapping(value = "/typeFuel/{typeFuel}")
    public ResponseEntity<Collection<CarEntity>> getAllByTypeFuel(@PathVariable @Positive @ApiParam("typeFuel params to give out a list of cars with such a type Fuel.") TypeFuel typeFuel){
        log.info("(/car/typeFuel/{typeFuel}), getAllByTypeFuel()");
        return ResponseEntity.ok(carServiceImpl.findAllByFuelType(typeFuel));
    }

    /**
     * Get price car by idCar.
     * @param carId params to give price.
     * @return  {@link ResponseEntity} price.
     */
    @ApiOperation(value = "Get price car by idCar.", notes = "")
    @GetMapping(value = "/getPrice/{carId}")
    public ResponseEntity getPriceById(@PathVariable @Positive @ApiParam("carId params to give price.") Long carId){
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
    @ApiOperation(value = "Set availability of car.", notes = "")
    @PostMapping("/setAvailability")
    public ResponseEntity setAvailability(@ApiParam("car id and flag model") CarIdAndFlag carIdAndFlag){
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
    @ApiOperation(value = "Set activation status.", notes = "")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/activateCar")
    public ResponseEntity setIsActivated(@ApiParam("car id and flag model") CarIdAndFlag carIdAndFlag){
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
    @ApiOperation(value = "Looking for cars with specified parameters.", notes = "")
    @GetMapping("/search")
    public ResponseEntity search(@RequestParam(value = "search") @ApiParam("search settings find for findAdd") String search) {
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
