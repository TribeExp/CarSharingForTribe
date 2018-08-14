package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.ConverterCars;
import com.basakdm.excartest.utils.ConverterUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
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

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userEntity));
    }

    @DeleteMapping ("/delete/{id}")
    public void delete(@PathVariable @Positive Long id){
        userService.delete(id);
    }

    @PostMapping ("/update")
    public void update(@RequestBody UserEntity userEntity){
        userService.update(userEntity);
    }


    @GetMapping(value = "/getPasswordById/{userId}")
    public String getPasswordById(@PathVariable @Positive Long userId){
        return userService.findById(userId).get().getPassword();
    }
    @GetMapping(value = "/getEmailById/{userId}")
    public String getEmailById(@PathVariable @Positive Long userId){
        return userService.findById(userId).get().getMail();
    }
    @GetMapping(value = "/getPhoto/{userId}")
    public String getPhotoById(@PathVariable @Positive Long userId){
        return userService.findById(userId).get().getPhoto();
    }
    @GetMapping(value = "/getPhone/{userId}")
    public String getPhoneById(@PathVariable @Positive Long userId){
        return userService.findById(userId).get().getPhoneNum();
    }
    @GetMapping(value = "/getterSetCar/{userId}")
    public ArrayList<Long> getSetCarById(@PathVariable @Positive Long userId){
        return userService.findById(userId).get().getSetIdCar();
    }

    /*@PostMapping(value = "/setterSetCar/{userId}/{idNewCar}")
    public void getSetCarById(@RequestBody @PathVariable @Positive Long userId, @PathVariable @Positive Long idNewCar){
        Optional<UserEntity> userEntity = userService.findById(userId);
        ArrayList<Long> car = userEntity.get().getSetIdCar();
        car.add(idNewCar);
        userEntity.get().setSetIdCar(car);
        userRepositoryDAO.save(userEntity);
    }*/





    /*@GetMapping(value = "/setterSetCar/{userId}")
    public int[] setSetCarById(@PathVariable @Positive Long userId){


        int[] oldCar = userService.findById(userId).get().getSetIdCar();

        return
    }*/


}
