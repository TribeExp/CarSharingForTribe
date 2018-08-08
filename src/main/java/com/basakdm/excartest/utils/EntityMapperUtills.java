package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity._CarEntity;

public class EntityMapperUtills {

    public static CarDTO mapCar(_CarEntity carEntity) {
        CarDTO carDto = new CarDTO();
        return carDto;
    }

    //TODO: make it in future
    public static _CarEntity mapCar(CarDTO carDto) {
        _CarEntity carEntity = new _CarEntity();
        return carEntity;
    }
}
