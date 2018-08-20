package com.admin_team.carsharing.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService{

    /**
     * send Registration Message
     * @param email - email user, who will send a message
     * @param password - password user, who will send a message
     * @return boolean, where true == sent
     */
    boolean sendRegistrationMessage(String email, String password);
}
