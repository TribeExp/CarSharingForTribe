package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.NotificationsRepositoryDAO;
import com.basakdm.excartest.entity.NotificationsEntity;
import com.basakdm.excartest.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    private NotificationsRepositoryDAO notificationsRepositoryDAO;

    @Override
    public Collection<NotificationsEntity> findAll() {
        return notificationsRepositoryDAO.findAll();
    }

    @Override
    public Optional<NotificationsEntity> findById(Long id) {
        return notificationsRepositoryDAO.findById(id);
    }

    @Override
    public NotificationsEntity create(NotificationsEntity notificationsEntity) {
        return notificationsRepositoryDAO.saveAndFlush(notificationsEntity);
    }

    @Override
    public void delete(long id) {
        Optional<NotificationsEntity> notifyOld = findById(id);
        if(notifyOld.isPresent()) notificationsRepositoryDAO.deleteById(id);
    }

    @Override
    public void update(NotificationsEntity notificationsEntity) {
        Long id = notificationsEntity.getId();
        Optional<NotificationsEntity> notifyOld = findById(id);
        if(notifyOld.isPresent()) notificationsRepositoryDAO.save(notificationsEntity);
    }
}
