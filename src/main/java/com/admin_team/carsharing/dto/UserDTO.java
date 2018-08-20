package com.admin_team.carsharing.dto;

import com.admin_team.carsharing.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String lastName;
    private String firstName;
    private String password;
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
    private String phoneNum;
    private HashSet<Long> setIdCar;
    private Boolean notify;
    private Set<Role> roles;
    private Boolean active;

}
