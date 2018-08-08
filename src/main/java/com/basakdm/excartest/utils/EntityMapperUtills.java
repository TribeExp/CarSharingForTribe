package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;

public class EntityMapperUtills {

    public static CarDTO mapCar(CarEntity carEntity) {
        CarDTO carDto = new CarDTO();
        return carDto;
    }

    //TODO: make it in future
    public static CarEntity mapCar(CarDTO carDto) {
        CarEntity carEntity = new CarEntity();
        return carEntity;
    }
}
