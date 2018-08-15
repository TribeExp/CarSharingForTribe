package com.basakdm.excartest.entity;

import com.basakdm.excartest.enum_ent.car_enum.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmissionType;


    @Column
    @Enumerated(EnumType.STRING)
    private CarBody carBody;
    @Column
    @Enumerated(EnumType.STRING)
    private DriveGear driveGear;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_engine")
    private TypeEngine engineType;
    @Column(name = "fuels")
    @Enumerated(EnumType.STRING)
    private TypeFuel fuelType;
    @Column
    private Double fuelConsumption;


    @Column(name = "condition_Information")
    private String conditionInformation;
    @Column
    private String insurance;
    @Column
    private Long priceLease;
    @Column(name = "ad_text")
    private String adText;
    @Column
    private Boolean isActivated;


    @Column
    private Long price;
    // под вопросом
    @Column
    private String typeUser;
    @Column
    private Long id_owner;
    @Column
    private String optionalAccessories;

    /* Возможно понадобятся
    @Column
    private Date calendarOfFree;
    @ManyToOne
    @JoinColumn(name = "user_id_boss")
    private UserEntity user_id_boss;
    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private UserEntity user_id_user;
    @Column
    private Boolean isFree;
    @Column
    private String causeOfRejected;*/



}
