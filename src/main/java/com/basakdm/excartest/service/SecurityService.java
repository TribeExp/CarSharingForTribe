package com.basakdm.excartest.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String findLoggedInEmail();
    boolean autoLogin(String email, String password);
}
