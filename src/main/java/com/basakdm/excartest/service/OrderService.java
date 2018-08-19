package com.basakdm.excartest.service;

import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface OrderService {

    /**
     * Get all Orders.
     * @return collection of OrderEntity.
     */
    Collection<OrderEntity> findAll();

    /**
     * Find Orders by id
     * @param id orders unique identifier.
     * @return Optional with order, if order was founded. Empty optional in opposite case.
     */
    Optional<OrderEntity> findById(Long id);

    /**
     * Create Order.
     * @param orderEntity params for create a new order.
     * @return Created order with id.
     */
    OrderEntity create(OrderEntity orderEntity);

    /**
     * Delete order by id.
     * @param id order params for delete a order.
     */
    void delete(long id);

    /**
     * Update order by id.
     * @param orderEntity order params for update a order.
     */
    void update(OrderEntity orderEntity);

    /**
     * Find Orders by id Car
     * @param idCar orders unique identifier.
     * @return Optional with order, if order was founded. Empty optional in opposite case.
     */
    Optional<OrderEntity> findByIdCar(Long idCar);

    /**
     * Activate order by id
     * @param isActivated true for activation
     * @param carId car id
     * @throws Exception if order not found
     */
    void activateOrder(Boolean isActivated, Long orderId) throws Exception;

    /**
     * Find orders by (isActivated = true)
     * @return {@link Collection<OrderEntity>}
     */
     Collection<OrderEntity> findAllByIsActivatedTrue();

    /**
     * Save new order inDB
     * @param orderEntity {@link OrderEntity}
     */
    void saveOrder(OrderEntity orderEntity);
}
