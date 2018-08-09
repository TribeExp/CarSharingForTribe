package com.basakdm.excartest.dto;

import com.basakdm.excartest.enum_ent.car_enum.Transmission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String number;
    private Integer mileage;
    private Integer seats;
    private Transmission transmissionType;

}