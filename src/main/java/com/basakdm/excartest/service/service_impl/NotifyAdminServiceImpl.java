package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.NotifyAdminRepositoryDAO;
import com.basakdm.excartest.entity.NotifyAdmin;
import com.basakdm.excartest.service.NotifyAdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NotifyAdminServiceImpl implements NotifyAdminService {

    @Autowired
    private NotifyAdminRepositoryDAO notifyAdminRepositoryDAO;

    @Override
    public List<NotifyAdmin> findAll() {
        return notifyAdminRepositoryDAO.findAll();
    }

    @Override
    public Optional<NotifyAdmin> findById(Long id) {
        return Optional.empty();
    }
}
