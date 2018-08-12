package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.enum_ent.Roles;
import com.basakdm.excartest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Override
    public List<UserEntity> findAll() {
        return userRepositoryDAO.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepositoryDAO.findById(id);
    }

    @Override
    public Optional<UserEntity> findByMail(String email) {
        return userRepositoryDAO.findByMailEquals(email);
    }

    @Override
    public UserEntity createUser(String email, String password) {
        //userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        UserEntity newUser = new UserEntity();
        newUser.setPassword(password);
        newUser.setMail(email);
        newUser.setActive(true);
        //newUser.setRole(Collections.singleton(Roles.USER));

        return userRepositoryDAO.saveAndFlush(newUser);
    }

}
