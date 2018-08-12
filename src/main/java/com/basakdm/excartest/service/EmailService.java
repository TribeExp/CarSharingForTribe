package com.basakdm.excartest.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService{
    boolean sendRegistrationMessage(String email, String password);
}
