package com.admin_team.carsharing.controller;

import com.admin_team.carsharing.entity.Role;
import com.admin_team.carsharing.request_models.EmailAndPassword;
import com.admin_team.carsharing.request_models.auth_models.Email;
import com.admin_team.carsharing.request_models.auth_models.LoginResponse;
import com.admin_team.carsharing.service.EmailService;
import com.admin_team.carsharing.service.UserService;
import com.admin_team.carsharing.utils.authentification.TokenProvider;
import com.admin_team.carsharing.utils.converters.ConverterUsers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(value = "Controller for interaction with the methods Auth", description = "The operations that can be performed with the auth table are in this controller")
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
    @ApiOperation(value = "Registration a new user in DB and send him the password.", notes = "")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @ApiParam("email new user") Email email) {
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

    /**
     * Registration a new user in DB with role ADMIN
     * @param email {@link Email} email new admin
     * @return {@link ResponseEntity} 500 if user already exists, and 201 if user created
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrationADMIN")
    public ResponseEntity<?> registrationADMIN(@RequestBody Email email) {
        //if user already exist
        String correctEmail = email.getEmail().toLowerCase();
        if (userService.findByMail(correctEmail).isPresent()) {
            log.info("(/auth/registrationADMIN), registrationADMIN() - user with email: " + correctEmail + " already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }
        String password = correctEmail.substring(0, 3) + email.hashCode()/1000;
        emailService.sendRegistrationMessage(correctEmail, password);
        log.info("(/auth/registrationADMIN), registrationADMIN() - password send to email: " + correctEmail);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterUsers.mapUser(userService.createAdmin(correctEmail, password)));
    }

    /**
     * Login user by email and password
     * @param emailAndPassword {@link EmailAndPassword} email and password model
     * @return {@link ResponseEntity} if 200 - return {@link LoginResponse}, ond 409 if authentication failed
     */
    @ApiOperation(value = "Login user by email and password.", notes = "")
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @ApiParam("email and password model") EmailAndPassword emailAndPassword) {
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
    @ApiOperation(value = "Get current user ID.", notes = "")
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
    @ApiOperation(value = "Get current user roles.", notes = "")
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
