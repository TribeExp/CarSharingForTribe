package com.basakdm.excartest.dto;

import com.basakdm.excartest.entity.UsersEntity;
import com.basakdm.excartest.enum_ent.car_enum.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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
    private TypeEngine typeEngine;
    private TypeFuel fuels;
    private Integer fuelConsumption;
    private String shortDescription;
    private String insurance;
    private String text;
    private Long priceLease;
    private Date calendarOfFree;
    private UsersEntity user_id_boss;
    private UsersEntity user_id_user;
    private String isFree;
    private String isActivated;
    private String causeOfRejected;
}