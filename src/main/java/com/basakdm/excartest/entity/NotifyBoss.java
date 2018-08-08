package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "notify_boss")
public class NotifyBoss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String text_notify;
    @Column
    private int type_of_notify;
}


