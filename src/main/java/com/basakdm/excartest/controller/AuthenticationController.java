package com.basakdm.excartest.controller;

import com.basakdm.excartest.entity.Role;
import com.basakdm.excartest.request_models.EmailAndPassword;
import com.basakdm.excartest.request_models.auth_models.LoginResponse;
import com.basakdm.excartest.request_models.auth_models.Email;
import com.basakdm.excartest.service.EmailService;
import com.basakdm.excartest.service.SecurityService;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.converters.ConverterUsers;
import com.basakdm.excartest.utils.authentification.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

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
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider jwtTokenUtil;

    @GetMapping()
    public ResponseEntity<?> main() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Main page");
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Email email) {
        //if user already exist
        String correctEmail = email.getEmail().toLowerCase();
        if (userService.findByMail(correctEmail).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }
        // password - take a first 3 characters from email and add email hash code
        String password = correctEmail.substring(0, 3) + email.hashCode();
        String testPassword = "test";
        emailService.sendRegistrationMessage(correctEmail, password);
        // securityService.autoLogin(userEntity.getMail(), userEntity.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterUsers.mapUser(userService.createUser(correctEmail, password)));
    }
    //--------------------DELETE before relese!!!-------------------------------
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

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody EmailAndPassword emailAndPassword) {
        String correctEmail = emailAndPassword.getEmail().toLowerCase();
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            correctEmail,
                            emailAndPassword.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponse loginResponse = new LoginResponse();
        try {
            loginResponse.setToken(jwtTokenUtil.generateToken(authentication));
            loginResponse.setCurrenUserId(userService.getCurrentUserId());
            loginResponse.setRoles(userService.getCurrentUserRoles().stream()
                    .map(Role::getRole).collect(Collectors.toSet()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/currentId")
    public ResponseEntity getCurrentUserId(){
        try {
            return ResponseEntity.ok(userService.getCurrentUserId());
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/currentRoles")
    public ResponseEntity getCurrentUserRoles(){
        try {
            Set<String> rolesName = userService.getCurrentUserRoles().stream()
                    .map(Role::getRole)
                    .collect(Collectors.toSet());
            return ResponseEntity.ok(rolesName);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    /*@PostMapping("/auth")
    public ResponseEntity auth(){
        try {
            return ResponseEntity.ok(userService.getAuthority(userService.getCurrentUser().get()));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }*/
}
