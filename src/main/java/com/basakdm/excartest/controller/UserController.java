package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.request_models.user_models.UserIdAndCarId;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.converters.ConverterUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get all user
     * @return {@link ResponseEntity<Collection<UserDTO>>}
     */
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
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable @Positive Long userId){
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
    @PostMapping ("/delete/{id}")
    public ResponseEntity delete(@PathVariable @Positive Long id){
        log.info("(users/delete/{id}), delete()");
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update users by id.
     * @param userEntity user params for update a users.
     * @return {@link ResponseEntity}
     */
    @PostMapping ("/update")
    public ResponseEntity update(@RequestBody UserEntity userEntity){
        log.info("(users/update), update()");
        userService.update(userEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * Get user password by id
     * @param userId user id
     * @return {@link ResponseEntity} password
     */
    @GetMapping(value = "/getPasswordById/{userId}")
    public ResponseEntity getPasswordById(@PathVariable @Positive Long userId){
        log.info("(users/getPasswordById/{userId}), getPasswordById()");
        return ResponseEntity.ok(userService.getPasswordById(userId));
    }

    /**
     * Get user Email by id
     * @param userId user id for find Email
     * @return {@link ResponseEntity} email
     */
    @GetMapping(value = "/getEmailById/{userId}")
    public ResponseEntity getEmailById(@PathVariable @Positive Long userId){
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
    @GetMapping(value = "/getPhoto/{userId}")
    public ResponseEntity getPhotoById(@PathVariable @Positive Long userId){
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
    @GetMapping(value = "/getPhone/{userId}")
    public ResponseEntity getPhoneById(@PathVariable @Positive Long userId){
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
    @GetMapping(value = "/getterSetCar/{userId}")
    public ResponseEntity<HashSet<Long>> getSetCarById(@PathVariable @Positive Long userId){
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
    public ResponseEntity setSetCarById(@RequestBody UserIdAndCarId userIdAndCarId){
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
    @GetMapping(value = "/getNotifyById/{userId}")
    public ResponseEntity getNotifyById(@PathVariable @Positive Long userId){
        log.info("(users/getNotifyById/{userId}), getNotifyById()");
        return userService.findById(userId)
                .map(u -> ResponseEntity.ok(u.getNotify()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
