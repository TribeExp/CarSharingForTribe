package com.basakdm.excartest.service;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.dto.UserDTO;
import com.basakdm.excartest.entity.UsersEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    /**
     * Get all user
     *
     * @return collection of users
     */
    Collection<UserDTO> findAll();

    /**
     * Find car by id
     *
     * @param id user unique identifier
     * @return Optional with user, if user was founded. Empty optional in opposite case
     */
    Optional<UserDTO> findById(Long id);

    /**
     * Create user.
     * @param userDTO user params for create a new user
     * @return Created user with id.
     */
    UserDTO createUser(UserDTO userDTO);
}
