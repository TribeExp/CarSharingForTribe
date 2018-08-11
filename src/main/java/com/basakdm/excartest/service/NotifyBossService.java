package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.NotifyBoss;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface NotifyBossService {

    Collection<NotifyBoss> findAll();
    Optional<NotifyBoss> findById(Long id);
    NotifyBoss create(NotifyBoss notifyBoss);
}
