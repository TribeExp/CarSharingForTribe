package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OrderRepositoryDAO extends JpaRepository<OrderEntity, Long> {
   Optional<OrderEntity> findByIdCar(Long idCar);
}
