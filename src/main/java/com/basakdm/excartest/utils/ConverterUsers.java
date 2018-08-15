package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConverterUsers {

        @Autowired
        private static UserService userService;

        public static UserDTO mapUser(UserEntity userEntity) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userEntity.getId());
            userDTO.setLastName(userEntity.getLastName());
            userDTO.setFirstName(userEntity.getFirstName());
            userDTO.setMail(userEntity.getMail());
            userDTO.setSeriesPassport(userEntity.getSeriesPassport());
            userDTO.setNumberSeria(userEntity.getNumberSeria());
            userDTO.setWhoGetPasport(userEntity.getWhoGetPasport());
            userDTO.setWhenGetPassport(userEntity.getWhenGetPassport());
            userDTO.setBirthDate(userEntity.getBirthDate());
            userDTO.setBirthPlace(userEntity.getBirthPlace());
            userDTO.setSerialDriveDoc(userEntity.getSerialDriveDoc());
            userDTO.setNumDriveDoc(userEntity.getNumDriveDoc());
            userDTO.setWhoGetDriveDoc(userEntity.getWhoGetDriveDoc());
            userDTO.setWhenGetDriveDoc(userEntity.getWhenGetDriveDoc());
            userDTO.setExpirtyDate(userEntity.getExpirtyDate());
            userDTO.setCategoryDriveDoc(userEntity.getCategoryDriveDoc());
            userDTO.setPhoneNum(userEntity.getPhoneNum());
            userDTO.setNotify(userEntity.getNotify());
            userDTO.setSetIdCar((userEntity.getSetIdCar()));
            return userDTO;
        }

        public static UserEntity mapUser(UserDTO userDTO) {
            UserEntity userEntity;
            Optional<UserEntity> userEntityOptional = userService.findById(userDTO.getId());

            if (userEntityOptional.isPresent()) userEntity = userEntityOptional.get();
            else throw new RuntimeException("Incorrect ID of user");

            userEntity.setId(userDTO.getId());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setMail(userDTO.getMail());
            userEntity.setSeriesPassport(userDTO.getSeriesPassport());
            userEntity.setNumberSeria(userDTO.getNumberSeria());
            userEntity.setWhoGetPasport(userDTO.getWhoGetPasport());
            userEntity.setWhenGetPassport(userDTO.getWhenGetPassport());
            userEntity.setBirthDate(userDTO.getBirthDate());
            userEntity.setBirthPlace(userDTO.getBirthPlace());
            userEntity.setSerialDriveDoc(userDTO.getSerialDriveDoc());
            userEntity.setNumDriveDoc(userDTO.getNumDriveDoc());
            userEntity.setWhoGetDriveDoc(userDTO.getWhoGetDriveDoc());
            userEntity.setWhenGetDriveDoc(userDTO.getWhenGetDriveDoc());
            userEntity.setExpirtyDate(userDTO.getExpirtyDate());
            userEntity.setCategoryDriveDoc(userDTO.getCategoryDriveDoc());
            userEntity.setPhoneNum(userDTO.getPhoneNum());
            userEntity.setNotify(userDTO.getNotify());
            userEntity.setSetIdCar(userDTO.getSetIdCar());
            return userEntity;
        }

}
