package com.basakdm.excartest.request_model.user_model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserIdAndCarId {
    private Long userId;
    private Long carId;
}
