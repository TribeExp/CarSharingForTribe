package com.basakdm.excartest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "users")
public class UserTest {

    @Id
    @GeneratedValue
    @Column (name = "user_id")
    int userId;

    @Column (name = "name")
    String name;

    @Column (name = "age")
    int age;

    public UserTest(){}
}
