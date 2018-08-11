package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConverterCars {

    @Autowired
    private static CarService carService;

    public static CarDTO mapCar(CarEntity carEntity) {
        CarDTO carDto = new CarDTO();
        carDto.setBrand(carEntity.getBrand());
        carDto.setId(carEntity.getId());
        carDto.setModel(carEntity.getModel());
        carDto.setYear(carEntity.getYear());
        carDto.setYear(carEntity.getYear());
        carDto.setMileage(carEntity.getMileage());
        carDto.setMileage(carEntity.getMileage());
        carDto.setSeats(carEntity.getSeats());
        carDto.setLocation(carEntity.getLocation());
        carDto.setPhoto(carEntity.getPhoto());
        carDto.setCarBody(carEntity.getCarBody());
        carDto.setDriveGear(carEntity.getDriveGear());
        carDto.setTransmissionType(carEntity.getTransmissionType());
        carDto.setEngineType(carEntity.getEngineType());
        carDto.setFuelType(carEntity.getFuelType());
        carDto.setFuelConsumption(carEntity.getFuelConsumption());
        carDto.setConditionInformation(carEntity.getConditionInformation());
        carDto.setInsurance(carEntity.getInsurance());
        carDto.setOptionalAccessories(carEntity.getOptionalAccessories());
        carDto.setAdText(carEntity.getAdText());
        carDto.setPriceLease(carEntity.getPriceLease());
        carDto.setCauseOfRejected(carEntity.getCauseOfRejected());
        return carDto;
    }

    //TODO: make it in future
    public static CarEntity mapCar(CarDTO carDto) {
        CarEntity carEntity;
        Optional<CarEntity> carEntityOptional = carService.findById(carDto.getId());

        if (carEntityOptional.isPresent()) carEntity = carEntityOptional.get();
        else throw new RuntimeException("Incorrect ID of car");

        carEntity.setBrand(carDto.getBrand());
        carEntity.setModel(carDto.getModel());
        carEntity.setYear(carDto.getYear());
        carEntity.setYear(carDto.getYear());
        carEntity.setMileage(carDto.getMileage());
        carEntity.setMileage(carDto.getMileage());
        carEntity.setSeats(carDto.getSeats());
        carEntity.setLocation(carDto.getLocation());
        carEntity.setPhoto(carDto.getPhoto());
        carEntity.setCarBody(carDto.getCarBody());
        carEntity.setDriveGear(carDto.getDriveGear());
        carEntity.setTransmissionType(carDto.getTransmissionType());
        carEntity.setEngineType(carDto.getEngineType());
        carEntity.setFuelType(carDto.getFuelType());
        carEntity.setFuelConsumption(carDto.getFuelConsumption());
        carEntity.setConditionInformation(carDto.getConditionInformation());
        carEntity.setInsurance(carDto.getInsurance());
        carEntity.setOptionalAccessories(carDto.getOptionalAccessories());
        carEntity.setAdText(carDto.getAdText());
        carEntity.setPriceLease(carDto.getPriceLease());
        carEntity.setCauseOfRejected(carDto.getCauseOfRejected());
        return carEntity;
    }
}
