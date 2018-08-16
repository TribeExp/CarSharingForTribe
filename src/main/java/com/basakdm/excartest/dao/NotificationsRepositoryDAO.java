package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepositoryDAO extends JpaRepository<NotificationsEntity, Long> {
}
