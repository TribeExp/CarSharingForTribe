package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.UsersEntity;

public class ConverterUsers {


        public static UserDTO mapUser(UsersEntity usersEntity) {
            UserDTO userDTO = new UserDTO();
            userDTO.setFirst_name(usersEntity.getFirst_name());
            userDTO.setLast_name(usersEntity.getLast_name());
            return userDTO;
        }

        public static UsersEntity mapUser(UserDTO userDTO) {
            UsersEntity usersEntity = new UsersEntity();
            return usersEntity;
        }

}
