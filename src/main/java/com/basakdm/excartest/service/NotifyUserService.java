package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.NotifyBoss;
import com.basakdm.excartest.entity.NotifyUser;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface NotifyUserService {

    Collection<NotifyUser> findAll();
    Optional<NotifyUser> findById(Long id);
    NotifyUser create(NotifyUser notifyUser);
    void delete(long id);

}
