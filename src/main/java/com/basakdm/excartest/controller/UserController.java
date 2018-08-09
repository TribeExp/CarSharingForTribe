package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.UsersEntity;
import com.basakdm.excartest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public Collection<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> findCarById(@PathVariable @Positive Long userId){
        return userService.findById(userId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/createUser")
    public UserDTO createUser(UserDTO userDTO){
        return userService.createUser(userDTO);
    }
}
