package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.entity.UsersEntity;
import com.basakdm.excartest.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTestServiceImpl implements UserTestService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Override
    public List<UsersEntity> findAll() {
        return userRepositoryDAO.findAll();
    }

    @Override
    public Optional<UsersEntity> findById(Long id) {
        return userRepositoryDAO.findById(id);
    }
}
