package com.admin_team.carsharing.dao;

import com.admin_team.carsharing.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OrderRepositoryDAO extends JpaRepository<OrderEntity, Long> {
   Optional<OrderEntity> findByIdCar(Long idCar);
   Collection<OrderEntity> findAllByIsActivatedTrue();
}
