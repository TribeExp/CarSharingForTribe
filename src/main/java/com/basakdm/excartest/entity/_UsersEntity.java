package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
public class _UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String last_name;
    @Column
    private String first_name;
    @Column
    private String middle_name;
    @Column
    private String password;
    @Column
    private String mail;
    @Column
    private String series_passport;
    @Column
    private long number_seria;
    @Column
    private String who_get_pasport;
    @Column
    private Date when_get_passport;
    @Column
    private Date birth_date;
    @Column
    private String birth_place;
    @Column
    private String serial_drive_doc;
    @Column
    private long num_drive_doc;
    @Column
    private String who_get_drive_doc;
    @Column
    private Date when_get_drive_doc;
    @Column
    private Date expirty_date;
    @Column
    private String category_drive_doc;
    @Column
    private String photo;
    @Column
    private long phone_num;
    @Column
    private boolean notify;
    @Column
    private long id_car;
    @Column
    private long price;
    @Column
    private long price_add;
    @Column
    private long fin_price;
    @Column
    private Date time_for_drive;
    @Column
    private String cause_add_price;
    @Column
    private String type_user;


    // TODO: Enums for ALLLLLLLLLLLLLLL
}




   /* @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private _Transmission transmissionType;*/

