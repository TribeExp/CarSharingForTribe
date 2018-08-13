package com.basakdm.excartest.service;


import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.enum_ent.car_enum.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface CarService  {

    /**
     * Get all cars.
     *
     * @return collection of cars.
     */
    Collection<CarEntity> findAll();

    /**
     * Find car by id
     *
     * @param id car unique identifier.
     * @return Optional with car, if car was founded. Empty optional in opposite case.
     */
    Optional<CarEntity> findById(Long id);

    /**
     * Create car.
     * @param carEntity car params for create a new car.
     * @return Created car with id.
     */
    CarEntity createCar(CarEntity carEntity);

    /**
     * Delete car by id.
     * @param id car params for delete a car.
     * @return  Void.
     */
    void delete(long id);

    void update(CarEntity carEntity);

    Collection<CarEntity> findAllByIsActivatedFalse();
    Collection<CarEntity> findAllByIsActivatedTrue();

    Collection<CarEntity> findAllByIsFreeFalse();
    Collection<CarEntity> findAllByIsFreeTrue();

    Collection<CarEntity> findAllByTransmissionType(Transmission transmission);
    Collection<CarEntity> findAllByCarBody(CarBody carBody);
    Collection<CarEntity> findAllByDriveGear(DriveGear driveGear);
    Collection<CarEntity> findAllByEngineType(TypeEngine typeEngine);
    Collection<CarEntity> findAllByFuelType(TypeFuel typeFuel);
}
