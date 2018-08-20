package com.admin_team.carsharing.dto;

import com.admin_team.carsharing.enum_ent.car_enum.*;
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
    private Transmission transmissionType;


    private CarBody carBody;
    private DriveGear driveGear;
    private TypeEngine engineType;
    private TypeFuel fuelType;
    private Double fuelConsumption;


    private String conditionInformation;
    private String insurance;
    private Long priceLease;
    private String adText;
    private Boolean isActivated;


    private Long price;
    private Long id_owner;
    private String optionalAccessories;
    private String calendarOfFree;
    private Boolean isFree;
}