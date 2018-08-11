package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.NotifyAdmin;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface NotifyAdminService {

    Collection<NotifyAdmin> findAll();
    Optional<NotifyAdmin> findById(Long id);
    NotifyAdmin create(NotifyAdmin notifyAdmin);
}
