package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long priceAdd;
    @Column
    private Long finPrice;
    @Column
    private Date fromWhatDate;
    @Column
    private Long idCar;
    @Column
    private Long idUser;
    @Column
    private Long idOwner;
    @Column
    private Integer amountOfDays;
    @Column
    private Boolean isActivated;
}
