package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.service.UserService;
import com.basakdm.excartest.utils.ConverterUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UserEntity createUser(UserEntity userEntity) {
        return userRepositoryDAO.saveAndFlush(userEntity);
    }

    @Override
    public void delete(long id) {
        Optional<UserEntity> userOld = findById(id);
        if(userOld.isPresent()) userRepositoryDAO.deleteById(id);
    }

    @Override
    public void update(UserEntity userEntity) {
        Long id = userEntity.getId();
        Optional<UserEntity> userOld = findById(id);
        if(userOld.isPresent()) userRepositoryDAO.save(userEntity);
    }

}
