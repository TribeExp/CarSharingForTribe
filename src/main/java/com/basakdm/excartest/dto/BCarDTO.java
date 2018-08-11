package com.basakdm.excartest.dto;

import com.basakdm.excartest.enum_ent.car_enum.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BCarDTO {

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
    //changed to double
    private Double fuelConsumption;
    //shortDescription - conditionInformation(данные о состоянии)
    private String conditionInformation;
    private String insurance;
    //optionalAccessories(доп аксессуары)
    private String optionalAccessories;
    //text - adText
    private String adText;
    private Long priceLease;
    private String causeOfRejected;
}
