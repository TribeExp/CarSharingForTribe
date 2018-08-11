package com.basakdm.excartest.entity;

import com.basakdm.excartest.enum_ent.TypeOfNotify;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

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
    private String middleName;
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
    private Long phoneNum;
    @Column
    private Boolean notify;
    @Column
    private Long idCar;
    @Column
    private Long price;
    @Column
    private Long priceAdd;
    @Column
    private Long finPrice;
    @Column
    private Date timeForDrive;
    @Column
    private String causeAddPrice;
    @Column
    private String typeUser;
    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfNotify typeOfNotify;


    // TODO: Enums for ALLLLLLLLLLLLLLL
}




   /* @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmissionType;*/

