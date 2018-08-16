package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.entity.Role;
import com.basakdm.excartest.request_models.EmailAndPassword;
import com.basakdm.excartest.request_models.auth_models.AuthToken;
import com.basakdm.excartest.request_models.auth_models.Email;
import com.basakdm.excartest.service.EmailService;
import com.basakdm.excartest.service.SecurityService;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.ConverterUsers;
import com.basakdm.excartest.utils.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    private UserRepositoryDAO userRepositoryDAO;

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
        emailService.sendRegistrationMessage(correctEmail, testPassword);
        // securityService.autoLogin(userEntity.getMail(), userEntity.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterUsers.mapUser(userService.createUser(correctEmail, testPassword)));
    }

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
        /*if (userService.findByMail(correctEmail).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }*/

        // password - take a first 3 characters from email and add email hash code
       // String password = correctEmail.substring(0, 3) + email.hashCode();
        String testPassword = "test";
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
        final String token = jwtTokenUtil.generateToken(authentication);

        //emailService.sendRegistrationMessage(correctEmail, testPassword);

        return ResponseEntity.ok(new AuthToken(token));
    }

    @GetMapping("/registration")
    public ResponseEntity<?> registration() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("You need to use POST for this page");
    }

    /*@GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("You need to use POST for this page");
    }*/

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmailAndPassword emailAndPassword) {
        if (securityService.autoLogin(emailAndPassword.getEmail(), emailAndPassword.getPassword())){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("User logged in");
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization failed");
    }*/

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

    @PostMapping("/auth")
    public ResponseEntity auth(){
        try {
            return ResponseEntity.ok(userService.getAuthority(userService.getCurrentUser().get()));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
