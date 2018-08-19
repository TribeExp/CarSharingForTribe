package com.basakdm.excartest.request_models.car_models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CarIdAndFlag {
    long carId;
    boolean flag;
}
