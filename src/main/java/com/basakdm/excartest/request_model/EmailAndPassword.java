package com.basakdm.excartest.request_model;

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
