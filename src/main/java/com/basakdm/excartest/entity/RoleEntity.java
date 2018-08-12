package com.basakdm.excartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

//DON'T USING
/*@Getter
@Setter
@Entity(name = "role")*/
public class RoleEntity {

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;*/
}
