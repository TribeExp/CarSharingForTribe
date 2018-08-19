package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private HashSet<Long> setIdCar;
    @Column
    private Boolean notify;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Column
    private Boolean active;

}

