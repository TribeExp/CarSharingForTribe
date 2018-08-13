package com.basakdm.excartest.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Long priceAdd;
    private Long finPrice;
    private Date timeForDrive;
    private Long id_car;
    private Long id_user;
    private Long id_owner;
    private Integer amount_of_days;
    private Date from_what_date;
}
