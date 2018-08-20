package com.admin_team.carsharing.service;

import com.admin_team.carsharing.entity.Role;
import com.admin_team.carsharing.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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
     * Adds new user in DB
     * @param email user email
     * @param password user password
     * @return {@link UserEntity}
     */
    UserEntity createUser(String email, String password);

    /**
     * Delete user by id.
     * @param id user params for delete a user.
     */
    void delete(long id);

    /**
     * Update users by id.
     * @param userEntity user params for update a users.
     */
    void update(UserEntity userEntity);

    /**
     * Get user password by id
     * @param userId user id
     * @return password
     */
    String getPasswordById(Long userId);

    /**
     * Find user by email
     * @param email user email
     * @return {@link Optional<UserEntity>}
     */
    Optional<UserEntity> findByMail(String email);

    /**
     * Get id current user
     * @return id current user
     * @throws Exception if user not found
     */
    long getCurrentUserId() throws Exception;

    /**
     * Get current user
     * @return {@link Optional<UserEntity>}
     * @throws Exception if user not found
     */
    Optional<UserEntity> getCurrentUser() throws Exception;

    /**
     * Get {@link Set< Role >} of current user
     * @return {@link Set<Role>} of current user
     * @throws Exception if user not found
     */
    Set<Role> getCurrentUserRoles() throws Exception;

    /**
     * Save user inDB
     * @param userEntity {@link UserEntity}
     * @return saved {@link UserEntity}
     */
    UserEntity saveUser(UserEntity userEntity);

    /**
     * Adds new user in DB with role ADMIN
     * @param email user email
     * @param password user password
     * @return {@link UserEntity}
     */
    UserEntity createAdmin(String email, String password);
}
