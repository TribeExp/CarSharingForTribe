package com.basakdm.excartest.service;

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
     * @return Created user with id.
     */
    UserEntity createUser(String email, String password);

    /**
     * Delete user by id.
     * @param id user params for delete a user.
     * @return  Void.
     */
    void delete(long id);

    /**
     * Update users by id.
     * @param userEntity user params for update a users.
     * @return  Void.
     */
    void update(UserEntity userEntity);
    String getPasswordById(Long userId);
    Optional<UserEntity> findByMail(String email);
}
