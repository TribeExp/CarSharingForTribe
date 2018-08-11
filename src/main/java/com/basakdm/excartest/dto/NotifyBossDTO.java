package com.basakdm.excartest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyBossDTO {
    private long id;
    private String text_notify;
    private int type_of_notify;
}
