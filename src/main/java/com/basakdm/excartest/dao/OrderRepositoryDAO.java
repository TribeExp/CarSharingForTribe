package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryDAO extends JpaRepository<OrderEntity, Long> {
}
