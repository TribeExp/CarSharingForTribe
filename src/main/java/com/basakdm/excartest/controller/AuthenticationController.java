package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.request_model.EmailAndPassword;
import com.basakdm.excartest.service.EmailService;
import com.basakdm.excartest.service.SecurityService;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.ConverterUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @GetMapping()
    public ResponseEntity<?> main() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Main page");
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody String email) {
        //if user already exist
        String correctEmail = email.toLowerCase();
        if (userService.findByMail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }
        // password - take a first 3 characters from email and add email hash code
        String password = email.substring(0, 3) + email.hashCode();
        String testPassword = "test";
        emailService.sendRegistrationMessage(email, testPassword);
        // securityService.autoLogin(userEntity.getMail(), userEntity.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterUsers.mapUser(userService.createUser(email, password)));
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
    public ResponseEntity<?> login(@RequestBody EmailAndPassword emailAndPassword) {
        System.out.println(emailAndPassword.getEmail()+" "+emailAndPassword.getPassword());
        if (securityService.autoLogin(emailAndPassword.getEmail(), emailAndPassword.getPassword())){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("User logged in");
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization failed");
    }
}
