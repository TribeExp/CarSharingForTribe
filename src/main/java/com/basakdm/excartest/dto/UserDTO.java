package com.basakdm.excartest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDTO {


    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String mail;
    private String seriesPassport;
    private Long numberSeria;
    private String whoGetPasport;
    private Date whenGetPassport;
    private Date birthDate;
    private String birthPlace;
    private String serialDriveDoc;
    private Long numDriveDoc;
    private String whoGetDriveDoc;
    private Date whenGetDriveDoc;
    private Date expirtyDate;
    private String categoryDriveDoc;
    private String photo;
    private String phoneNum;
    private Boolean notify;
    private Long idCar;
    private Long price;
    private Date timeForDrive;
    private Set<Long> setIdCar;

}
