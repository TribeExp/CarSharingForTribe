package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.OrderRepositoryDAO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.OrderEntity;
import com.basakdm.excartest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepositoryDAO orderRepositoryDAO;


    @Override
    public Collection<OrderEntity> findAll() {
        return orderRepositoryDAO.findAll();
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return orderRepositoryDAO.findById(id);
    }

    @Override
    public OrderEntity create(OrderEntity orderEntity) {
        return orderRepositoryDAO.saveAndFlush(orderEntity);
    }

    @Override
    public void delete(long id) {
        Optional<OrderEntity> orderOld = findById(id);
        if(orderOld.isPresent()) orderRepositoryDAO.deleteById(id);
    }

    @Override
    public void update(OrderEntity orderEntity) {
        Long id = orderEntity.getId();
        Optional<OrderEntity> orderOld = findById(id);
        if(orderOld.isPresent()) orderRepositoryDAO.save(orderEntity);
    }

    @Override
    public Optional<OrderEntity> findByIdCar(Long idCar) {
        return orderRepositoryDAO.findByIdCar(idCar);
    }

    @Override
    public void activateOrder(Boolean isActivated, Long orderId) throws Exception {
        OrderEntity orderEntity = findById(orderId)
                .orElseThrow(() -> new Exception("Car with id: " + orderId + " not found"));
        orderEntity.setIsActivated(true);
        update(orderEntity);
    }

    @Override
    public Collection<OrderEntity> findAllByIsActivatedTrue() {
        return orderRepositoryDAO.findAllByIsActivatedTrue();
    }

    @Override
    public void saveOrder(OrderEntity orderEntity) {
        orderRepositoryDAO.saveAndFlush(orderEntity);
    }

}
