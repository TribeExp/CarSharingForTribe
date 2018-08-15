package com.basakdm.excartest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Long priceAdd;
    private Long finPrice;
    private Date from_what_date;
    private Long id_car;
    private Long id_user;
    private Long id_owner;
    private Integer amount_of_days;
}
