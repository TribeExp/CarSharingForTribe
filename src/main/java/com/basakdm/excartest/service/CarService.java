package com.basakdm.excartest.service;


import com.basakdm.excartest.entity.CarEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService  {

    /**
     * Get all cars
     *
     * @return collection of cars
     */
    List<CarEntity> findAll();

    /*List<CarDTO> findByName(String name);

    *//**
     * Find car by id
     *
     * @param id car unique identifier
     * @return Optional with car, if car was founded. Empty optional in opposite case
     *//*
    Optional<CarDTO> getCarById(Long id);

    *//**
     * Create car.
     * @param carDto car params for create a new car
     * @return Created car with id.
     *//*
    CarDTO createCar(CarDTO carDto);*/
}
