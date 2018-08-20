package com.admin_team.carsharing.request_models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class EmailAndPassword {
    private String email;
    private String password;
}
