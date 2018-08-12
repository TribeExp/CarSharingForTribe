package com.basakdm.excartest.service;

import com.basakdm.excartest.dto.UserDTO;
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
     * Find user by id
     *
     * @param id user unique identifier
     * @return Optional with user, if user was founded. Empty optional in opposite case
     */
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByMail(String email);

    /**
     * Create user.
     * @param email email for sending user password
     * @return Created user.
     */
    UserEntity createUser(String email, String password);
}
