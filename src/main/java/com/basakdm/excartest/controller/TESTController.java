package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.TESTRepository;
import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.TESTEntity;
import com.basakdm.excartest.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/tests")
public class TESTController {

    @Autowired
    private TESTRepository testRepository;

    @RequestMapping(value = "/all")
    List<TESTEntity> findAll(){
        return testRepository.findAll();
    }

    @GetMapping(value = "/{testId}")
    public ResponseEntity<TESTEntity> findById(@PathVariable @Positive Long testId){
        return testRepository.findById(testId)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/testCreating")
    public TESTEntity createUser(TESTEntity testEntity){
        return testRepository.save(testEntity);
    }
}
