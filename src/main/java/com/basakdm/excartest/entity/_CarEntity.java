package com.basakdm.excartest.entity;

import com.basakdm.excartest.enum_ent._Transmission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class _CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private long year;
    @Column
    private String state_num;
    @Column
    private long mileage;
    @Column
    private int seats;
    @Column
    private String location;
    @Column
    private String photo;
    @Column
    private String car_body;
    @Column
    private String drive_gear;
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private _Transmission transmissionType;
    @Column
    private String type_engine;
    @Column
    private String fuels;
    @Column
    private int fuel_consumption;
    @Column
    private String short_description;
    @Column
    private String insurance;
    @Column
    private String text;
    @Column
    private long price_lease;
    @Column
    private Date calendar_of_free;
    @ManyToOne
    @JoinColumn(name = "user_id_boss")
    private _UsersEntity user_id_boss;
    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private _UsersEntity user_id_user;
    @Column
    private boolean free;

}
