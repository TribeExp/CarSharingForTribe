package com.basakdm.excartest.controller;

import com.basakdm.excartest.entity.Role;
import com.basakdm.excartest.request_models.EmailAndPassword;
import com.basakdm.excartest.request_models.auth_models.LoginResponse;
import com.basakdm.excartest.request_models.auth_models.Email;
import com.basakdm.excartest.service.EmailService;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.converters.ConverterUsers;
import com.basakdm.excartest.utils.authentification.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider jwtTokenUtil;

    /**
     * Registration a new user in DB and send him the password
     * @param email {@link Email} email new user
     * @return {@link ResponseEntity} 500 if user already exists, and 201 if user created
     */
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Email email) {
        String correctEmail = email.getEmail().toLowerCase();
        if (userService.findByMail(correctEmail).isPresent()) {
            log.info("(/auth/registration), registration() - user with email: " + correctEmail + " already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email: " + correctEmail + " already exists");
        }

        // password - take a first 3 characters from email and add email hash code divided by 1000
        String password = correctEmail.substring(0, 3) + email.hashCode()/1000;

        userService.createUser(correctEmail, password);
        log.info("(/auth/registration), registration() - user with email: " + correctEmail + " added in DB");
        emailService.sendRegistrationMessage(correctEmail, password);
        log.info("(/auth/registration), registration() - password send to email: " + correctEmail);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //--------------------DELETE before release!!!-------------------------------
    @PostMapping("/registrationADMIN")
    public ResponseEntity<?> registrationADMIN(@RequestBody Email email) {
        //if user already exist
        String correctEmail = email.getEmail().toLowerCase();
        if (userService.findByMail(correctEmail).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }
        String testPassword = "root";
        emailService.sendRegistrationMessage(correctEmail, testPassword);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterUsers.mapUser(userService.createAdmin(correctEmail, testPassword)));
    }

    /**
     * Login user by email and password
     * @param emailAndPassword {@link EmailAndPassword} email and password model
     * @return {@link ResponseEntity} if 200 - return {@link LoginResponse}, ond 409 if authentication failed
     */
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody EmailAndPassword emailAndPassword) {
        String correctEmail = emailAndPassword.getEmail().toLowerCase();
        LoginResponse loginResponse;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            correctEmail,
                            emailAndPassword.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            loginResponse = new LoginResponse();
            loginResponse.setToken(jwtTokenUtil.generateToken(authentication));
            loginResponse.setCurrenUserId(userService.getCurrentUserId());
            loginResponse.setRoles(userService.getCurrentUserRoles().stream()
                    .map(Role::getRole).collect(Collectors.toSet()));
        } catch (Exception e) {
            log.info("(/auth/login), login() - authentication failed with exception: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        log.info("(/auth/login), login() - the user: " + correctEmail + " has successfully logged in");
        return ResponseEntity.ok(loginResponse);
    }

    /**
     * Get current user ID
     * @return {@link ResponseEntity} 200 - return current user id, and 404 - if user not found
     */
    @GetMapping("/currentId")
    public ResponseEntity getCurrentUserId(){
        try {
            log.info("(/auth/currentId), getCurrentUserId()");
            return ResponseEntity.ok(userService.getCurrentUserId());
        } catch (Exception e) {
            log.info("(/auth/currentId), getCurrentUserId() - failed with exception: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get current user roles
     * @return {@link ResponseEntity} 200 - return current user roles, and 404 - if user not found
     */
    @GetMapping("/currentRoles")
    public ResponseEntity getCurrentUserRoles(){
        try {
            Set<String> rolesName = userService.getCurrentUserRoles().stream()
                    .map(Role::getRole)
                    .collect(Collectors.toSet());
            log.info("(/auth/currentRoles), getCurrentUserRoles()");
            return ResponseEntity.ok(rolesName);
        } catch (Exception e) {
            log.info("(/auth/currentRoles), getCurrentUserRoles() - failed with exception: " + e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

}
