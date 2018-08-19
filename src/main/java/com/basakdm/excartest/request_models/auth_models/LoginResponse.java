package com.basakdm.excartest.request_models.auth_models;

import com.basakdm.excartest.entity.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class LoginResponse {

    private String token;
    private Long currenUserId;
    private Set<String> roles;
}
