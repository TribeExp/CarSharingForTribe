package com.basakdm.excartest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationsDTO {

    private long id;
    private String text_notify;
    private Long from_whom_id;
    private Long to_whom_id;
    private Long order_id;
}
