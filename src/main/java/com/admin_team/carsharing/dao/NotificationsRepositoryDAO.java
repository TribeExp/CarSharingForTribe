package com.admin_team.carsharing.dao;

import com.admin_team.carsharing.entity.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepositoryDAO extends JpaRepository<NotificationsEntity, Long> {
}
