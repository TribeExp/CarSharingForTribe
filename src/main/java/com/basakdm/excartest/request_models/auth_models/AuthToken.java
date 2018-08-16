package com.basakdm.excartest.request_models.auth_models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AuthToken {

    private String token;

    public AuthToken(){

    }
    public AuthToken(String token){
        this.token = token;
    }
}
