package com.basakdm.excartest.entity;

import com.basakdm.excartest.enum_ent.car_enum.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private Long year;
    @Column
    private String stateNum;
    @Column
    private Long mileage;
    @Column
    private Integer seats;
    @Column
    private String location;
    @Column
    private String photo;
    @Column
    private CarBody carBody;
    @Column
    private DriveGear driveGear;
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmissionType;
    @Column
    private TypeEngine typeEngine;
    @Column
    private TypeFuel fuels;
    @Column
    private Integer fuelConsumption;
    @Column
    private String shortDescription;
    @Column
    private String insurance;
    @Column
    private String text;
    @Column
    private Long priceLease;
    @Column
    private Date calendarOfFree;
    @ManyToOne
    @JoinColumn(name = "user_id_boss")
    private UsersEntity user_id_boss;
    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private UsersEntity user_id_user;
    @Column
    private String isFree;
    @Column
    private String isActivated;
    @Column
    private String causeOfRejected;

}
