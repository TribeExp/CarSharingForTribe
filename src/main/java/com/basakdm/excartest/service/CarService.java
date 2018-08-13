package com.basakdm.excartest.service;


import com.basakdm.excartest.entity.CarEntity;
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
}
