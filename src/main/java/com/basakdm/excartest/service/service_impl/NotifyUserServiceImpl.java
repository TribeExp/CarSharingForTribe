package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.NotifyUserRepositoryDAO;
import com.basakdm.excartest.entity.NotifyUser;
import com.basakdm.excartest.service.NotifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotifyUserServiceImpl implements NotifyUserService {

    @Autowired
    private NotifyUserRepositoryDAO notifyUserRepositoryDAO;

    @Override
    public List<NotifyUser> findAll() {
        return notifyUserRepositoryDAO.findAll();
    }

    @Override
    public Optional<NotifyUser> findById(Long id) {
        return notifyUserRepositoryDAO.findById(id);
    }

    @Override
    public NotifyUser create(NotifyUser notifyUser) {
        return notifyUserRepositoryDAO.saveAndFlush(notifyUser);
    }

    @Override
    public void delete(long id) {
        notifyUserRepositoryDAO.deleteById(id);
    }
}



