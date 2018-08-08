package com.basakdm.excartest.service;


import com.basakdm.excartest.dto.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarService  {

    /**
     * Get all cars
     *
     * @return collection of cars
     */
    //List<CarDTO> findAll();

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
