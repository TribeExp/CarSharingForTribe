package com.admin_team.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationsDTO {

    private long id;
    private String textNotify;
    private Long fromWhomId;
    private Long toWhomId;
    private Long orderId;
}
