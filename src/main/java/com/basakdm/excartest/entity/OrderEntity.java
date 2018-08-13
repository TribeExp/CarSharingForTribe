package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

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
    private Date timeForDrive;
    @Column
    private Long id_car;
    @Column
    private Long id_user;
    @Column
    private Long id_owner;
    @Column
    private Integer amount_of_days;
    @Column
    private Date from_what_date;
}
