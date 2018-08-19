package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.NotificationsEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface NotificationsService {

    /**
     * Get all Notifications.
     * @return collection of NotificationsEntity.
     */
    Collection<NotificationsEntity> findAll();

    /**
     * Find notifications by id
     * @param id notification unique identifier.
     * @return Optional with notifications, if notifications was founded. Empty optional in opposite case.
     */
    Optional<NotificationsEntity> findById(Long id);

    /**
     * Create notification.
     * @param notificationsEntity params for create a new notification.
     * @return Created notification with id.
     */
    NotificationsEntity create(NotificationsEntity notificationsEntity);

    /**
     * Delete notification by id.
     * @param id notification params for delete a notification.
     */
    void delete(long id);

    /**
     * Update notification by id.
     * @param notificationsEntity notification params for update a notifications.
     */
    void update(NotificationsEntity notificationsEntity);
}
