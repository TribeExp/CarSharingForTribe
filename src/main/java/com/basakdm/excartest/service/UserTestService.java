package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.UsersEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserTestService {

    List<UsersEntity> findAll();
    Optional<UsersEntity> findById(Long id);
}
