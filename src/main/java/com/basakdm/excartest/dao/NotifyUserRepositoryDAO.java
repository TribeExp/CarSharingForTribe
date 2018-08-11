package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.NotifyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifyUserRepositoryDAO extends JpaRepository<NotifyUser, Long> {
}
