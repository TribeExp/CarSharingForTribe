package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String password;
    @Column
    private String mail;
    @Column
    private String seriesPassport;
    @Column
    private Long numberSeria;
    @Column
    private String whoGetPasport;
    @Column
    private Date whenGetPassport;
    @Column
    private Date birthDate;
    @Column
    private String birthPlace;
    @Column
    private String serialDriveDoc;
    @Column
    private Long numDriveDoc;
    @Column
    private String whoGetDriveDoc;
    @Column
    private Date whenGetDriveDoc;
    @Column
    private Date expirtyDate;
    @Column
    private String categoryDriveDoc;
    @Column
    private String photo;
    @Column
    private String phoneNum;
    @Column
    private Boolean notify;
    @Column
    private Long idCar;
    @Column
    private Long price;
    @Column
    private String causeAddPrice;
    @Column
    private String typeUser;
    @Column
    private ArrayList<Long> setIdCar;

}

