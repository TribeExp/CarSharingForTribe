package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.NotificationsEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface NotificationsService {

    Collection<NotificationsEntity> findAll();
    Optional<NotificationsEntity> findById(Long id);
    NotificationsEntity create(NotificationsEntity notificationsEntity);
    void delete(long id);
    void update(NotificationsEntity notificationsEntity);
}
