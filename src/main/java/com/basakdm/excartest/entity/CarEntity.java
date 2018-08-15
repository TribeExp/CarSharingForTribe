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
    @Enumerated(EnumType.STRING)
    private CarBody carBody;
    @Column
    @Enumerated(EnumType.STRING)
    private DriveGear driveGear;
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmissionType;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_engine")
    private TypeEngine engineType;
    @Column(name = "fuels")
    @Enumerated(EnumType.STRING)
    private TypeFuel fuelType;
    @Column
    private Double fuelConsumption;
    @Column(name = "short_description")
    private String conditionInformation;
    @Column
    private String insurance;
    @Column
    private String optionalAccessories;
    @Column(name = "text")
    private String adText;
    @Column
    private Long priceLease;
    @Column
    private Date freeFrom;
    @Column
    private Date freeTo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner_id;
    /*@ManyToOne
    @JoinColumn(name = "user_id_user")
    private UserEntity user_id_user;*/
    @Column
    private Boolean isFree;
    @Column
    private Boolean isActivated;
    @Column
    private String causeOfRejected;//??????????

}
