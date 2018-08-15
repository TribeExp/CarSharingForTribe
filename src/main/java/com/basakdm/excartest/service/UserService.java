package com.basakdm.excartest.service;

import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface UserService {

    /**
     * Get all user
     *
     * @return collection of users
     */
    Collection<UserEntity> findAll();

    /**
     * Find car by id
     *
     * @param id user unique identifier
     * @return Optional with user, if user was founded. Empty optional in opposite case
     */
    Optional<UserEntity> findById(Long id);

    /**
     * Create user.
     * @param userEntity user params for create a new user
     * @return Created user with id.
     */
    UserEntity createUser(UserEntity userEntity);

    void delete(long id);

    void update(UserEntity userEntity);
}
