package com.basakdm.excartest.controller;

import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.service.SecurityService;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.ConverterUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public ResponseEntity<?> main() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Main page");
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserEntity userEntity) {

        //if user already exist
        if (userService.findByMail(userEntity.getMail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }

        // securityService.autoLogin(userEntity.getMail(), userEntity.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterUsers.mapUser(userService.createUser(userEntity)));
    }

    @GetMapping("/registration")
    public ResponseEntity<?> registration() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("You need to use POST for this page");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("You need to use POST for this page");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(String email, String password) {
        if (securityService.autoLogin(email, password)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("User logged in");
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization failed");
    }
}
