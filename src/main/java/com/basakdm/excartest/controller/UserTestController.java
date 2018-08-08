package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.UserTest;
import com.basakdm.excartest.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserTestController {

    @Autowired
    UserTestService userTestService;

    @GetMapping("/all")
    public List<UserTest> findAll(){
        return userTestService.findAll();
    }
}
