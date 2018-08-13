package com.basakdm.excartest.dto;

import com.basakdm.excartest.enum_ent.car_enum.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private Long id;
    private String brand;
    private String model;
    private Long year;
    private String stateNum;
    private Long mileage;
    private Integer seats;
    private String location;
    private String photo;
    private CarBody carBody;
    private DriveGear driveGear;
    private Transmission transmissionType;
    private TypeEngine engineType;
    private TypeFuel fuelType;
    private Double fuelConsumption;
    private String conditionInformation;
    private String insurance;
    private String optionalAccessories;
    private String adText;
    private Long priceLease;
    private Boolean isFree;
    private Boolean isActivated;
}