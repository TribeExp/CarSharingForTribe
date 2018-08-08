package com.basakdm.excartest.service;

import com.basakdm.excartest.dao.UserRepository;
import com.basakdm.excartest.dto.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTestServiceImpl implements UserTestService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserTest> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserTest> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<UserTest> findById(Long id) {
        return userRepository.findById(id);
    }
}
