package com.admin_team.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Long priceAdd;
    private Long finPrice;
    private Date fromWhatDate;
    private Long idCar;
    private Long idUser;
    private Long idOwner;
    private Integer amountOfDays;
}
