package com.admin_team.carsharing.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "notifications")
public class NotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String textNotify;
    @Column
    private Long fromWhomId;
    @Column
    private Long toWhomId;
    @Column
    private Long orderId;
}
