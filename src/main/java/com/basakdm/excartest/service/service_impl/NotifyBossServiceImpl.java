package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.NotifyBossRepositoryDAO;
import com.basakdm.excartest.entity.NotifyBoss;
import com.basakdm.excartest.service.NotifyBossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotifyBossServiceImpl implements NotifyBossService {

    @Autowired
    private NotifyBossRepositoryDAO notifyBossRepositoryDAO;

    @Override
    public List<NotifyBoss> findAll() {
        return notifyBossRepositoryDAO.findAll();
    }

    @Override
    public Optional<NotifyBoss> findById(Long id) {
        return notifyBossRepositoryDAO.findById(id);
    }

    @Override
    public NotifyBoss create(NotifyBoss notifyBoss) {
        return notifyBossRepositoryDAO.saveAndFlush(notifyBoss);
    }
}
