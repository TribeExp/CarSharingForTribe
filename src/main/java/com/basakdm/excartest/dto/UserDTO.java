package com.basakdm.excartest.dto;

import com.basakdm.excartest.enum_ent.car_enum.Transmission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UserDTO {

    private String last_name;
    private String first_name;
    private String middle_name;
    private String password;
    private String mail;
    private String series_passport;
    private long number_seria;

}
