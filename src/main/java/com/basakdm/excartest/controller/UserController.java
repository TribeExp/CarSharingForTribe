package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.service.SecurityService;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.ConverterUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Collection<UserDTO> findAll(){
        return userService.findAll().stream()
                .map(ConverterUsers::mapUser)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable @Positive Long userId){
        return userService.findById(userId)
                .map(ConverterUsers::mapUser)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/createUser")
    public UserEntity createUser(UserEntity userEntity){
        return userService.createUser(userEntity);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(UserEntity userEntity) {

        //if user already exist
        if (userService.findByMail(userEntity.getMail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }

       // securityService.autoLogin(userEntity.getMail(), userEntity.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterUsers.mapUser(userService.createUser(userEntity)));
    }
}
