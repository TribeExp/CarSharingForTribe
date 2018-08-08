package com.basakdm.excartest.service;

import com.basakdm.excartest.dto.UserTest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserTestService {

    List<UserTest> findAll();
    List<UserTest> findByName(String name);
    Optional<UserTest> findById(Long id);
}
