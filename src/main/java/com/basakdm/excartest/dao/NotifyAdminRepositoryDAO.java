package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.NotifyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyAdminRepositoryDAO extends JpaRepository<NotifyAdmin, Long> {
}