package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class NotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String text_notify;
    @Column
    private Long from_whom_id;
    @Column
    private Long to_whom_id;
    @Column
    private Long order_id;
}
