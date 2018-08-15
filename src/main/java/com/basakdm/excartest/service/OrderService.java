package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface OrderService {

    Collection<OrderEntity> findAll();
    Optional<OrderEntity> findById(Long id);
    OrderEntity create(OrderEntity orderEntity);
    void delete(long id);
    void update(OrderEntity orderEntity);

}
