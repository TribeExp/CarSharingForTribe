package com.admin_team.carsharing.controller;

import com.admin_team.carsharing.dto.UserDTO;
import com.admin_team.carsharing.entity.UserEntity;
import com.admin_team.carsharing.request_models.user_models.UserIdAndCarId;
import com.admin_team.carsharing.service.UserService;
import com.admin_team.carsharing.utils.converters.ConverterUsers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
@Api(value = "Controller for interaction with the methods user", description = "The operations that can be performed with the user table are in this controller")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get all user
     * @return {@link ResponseEntity<Collection<UserDTO>>}
     */
    @ApiOperation(value = "Outputting the entire list of user in the user table.", notes = "")
    @GetMapping("/all")
    public  ResponseEntity<Collection<UserDTO>> findAll(){
        log.info("(users/all/), findAll()");
        return ResponseEntity.ok(userService.findAll().stream()
                .map(ConverterUsers::mapUser)
                .collect(Collectors.toList()));
    }

    /**
     * Find car by id
     * @param userId user unique identifier
     * @return Optional with user, if user was founded. Empty optional in opposite case
     */
    @ApiOperation(value = "Find car by id.", notes = "")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable @Positive @ApiParam("userId user unique identifier") Long userId){
        log.info("(users/{userId}/), findUserById()");
        return userService.findById(userId)
                .map(ConverterUsers::mapUser)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete user by id.
     * @param id user params for delete a user.
     * @return {@link ResponseEntity}
     */
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete user by id.", notes = "")
    @PostMapping ("/delete/{id}")
    public ResponseEntity delete(@PathVariable @Positive @ApiParam("id user params for delete a user") Long id){
        log.info("(users/delete/{id}), delete()");
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update users by id.
     * @param userEntity user params for update a users.
     * @return {@link ResponseEntity}
     */
    @ApiOperation(value = "Update users by id.", notes = "")
    @PostMapping ("/update/{userId}")
    public ResponseEntity update(@RequestBody @ApiParam("userEntity user params for update a users") UserEntity userEntity,
                                 @PathVariable @Positive @ApiParam("id user params for update a user") Long userId){
        log.info("(users/update), update()");
        userEntity.setId(userId);
        userService.update(userEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * Get user password by id
     * @param userId user id
     * @return {@link ResponseEntity} password
     */
    @ApiOperation(value = "Get user password by id.", notes = "")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/getPasswordById/{userId}")
    public ResponseEntity getPasswordById(@PathVariable @Positive @ApiParam("userId user id") Long userId){
        log.info("(users/getPasswordById/{userId}), getPasswordById()");
        return ResponseEntity.ok(userService.getPasswordById(userId));
    }

    /**
     * Get user Email by id
     * @param userId user id for find Email
     * @return {@link ResponseEntity} email
     */
    @ApiOperation(value = "Get user Email by id.", notes = "")
    @GetMapping(value = "/getEmailById/{userId}")
    public ResponseEntity getEmailById(@PathVariable @Positive @ApiParam("userId user id for find Email") Long userId){
        log.info("(users/getEmailById/{userId}), getEmailById()");
        return userService.findById(userId)
                .map(u -> ResponseEntity.ok(u.getMail()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get user Photo by id
     * @param userId user id for find Photo
     * @return {@link ResponseEntity} photo url
     */
    @ApiOperation(value = "Get user Photo by id.", notes = "")
    @GetMapping(value = "/getPhoto/{userId}")
    public ResponseEntity getPhotoById(@PathVariable @Positive @ApiParam("userId user id for find Photo") Long userId){
        log.info("(users/getPhoto/{userId}), getPhotoById()");
        return userService.findById(userId)
                .map(u -> ResponseEntity.ok(u.getPhoto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get user Phone by id
     * @param userId user id for find Phone
     * @return {@link ResponseEntity} phone
     */
    @ApiOperation(value = "Get user Phone by id.", notes = "")
    @GetMapping(value = "/getPhone/{userId}")
    public ResponseEntity getPhoneById(@PathVariable @Positive @ApiParam("userId user id for find Phone") Long userId){
        log.info("(users/getPhone/{userId}), getPhoneById()");
        return userService.findById(userId)
                .map(u -> ResponseEntity.ok(u.getPhoneNum()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get SetCar by id
     * @param userId user id for find set car
     * @return {@link ResponseEntity<HashSet<Long>>}
     */
    @ApiOperation(value = "Get SetCar by id.", notes = "")
    @GetMapping(value = "/getterSetCar/{userId}")
    public ResponseEntity<HashSet<Long>> getSetCarById(@PathVariable @Positive @ApiParam("userId user id for find set car") Long userId){
        log.info("(users/getterSetCar/{userId}), getSetCarById()");
        return userService.findById(userId)
                .map(u -> ResponseEntity.ok(u.getSetIdCar()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Set SetCar by id, add to the set of cars of the landlord, one more car
     * @param userIdAndCarId user id
     * @return {@link ResponseEntity}
     */
    @PostMapping(value = "/setNewIdCar")
    @ApiOperation(value = "Set SetCar by id, add to the set of cars of the landlord, one more car.", notes = "")
    public ResponseEntity setSetCarById(@RequestBody @ApiParam("userIdAndCarId user id") UserIdAndCarId userIdAndCarId){
        UserEntity foundUser = null;
        try {
            foundUser = userService.findById(userIdAndCarId.getUserId()).orElseThrow(() -> new Exception("User not found"));
        } catch (Exception e) {
            log.info("(users/setNewIdCar), setSetCarById() - user not found: ");
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        HashSet<Long> set = foundUser.getSetIdCar();
        set.add(userIdAndCarId.getCarId());
        foundUser.setSetIdCar(set);
        log.info("(users/setNewIdCar), getSetCarById()");
        return ResponseEntity.ok(ConverterUsers.mapUser(userService.saveUser(foundUser)));
    }

    /**
     * We get a field that shows whether a new notification has arrived or not (True / False)
     * @param userId id of the user of which we look at the field
     * @return {@link ResponseEntity}@return Boolean
     */
    @ApiOperation(value = "We get a field that shows whether a new notification has arrived or not (True / False).", notes = "")
    @GetMapping(value = "/getNotifyById/{userId}")
    public ResponseEntity getNotifyById(@PathVariable @Positive  @ApiParam("userId id of the user of which we look at the field") Long userId){
        log.info("(users/getNotifyById/{userId}), getNotifyById()");
        return userService.findById(userId)
                .map(u -> ResponseEntity.ok(u.getNotify()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
