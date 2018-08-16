package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.OrderDTO;
import com.basakdm.excartest.entity.OrderEntity;
import com.basakdm.excartest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConvertOrders {

    @Autowired
    private static OrderService orderService;

    public static OrderDTO mapOrder(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(orderEntity.getId());
        orderDTO.setPriceAdd(orderEntity.getPriceAdd());
        orderDTO.setFinPrice(orderEntity.getFinPrice());
        orderDTO.setFromWhatDate(orderEntity.getFromWhatDate());
        orderDTO.setIdCar(orderEntity.getIdCar());
        orderDTO.setIdUser(orderEntity.getIdUser());
        orderDTO.setIdOwner(orderEntity.getIdOwner());
        orderDTO.setAmountOfDays(orderEntity.getAmountOfDays());

        return orderDTO;
    }

    public static OrderEntity mapOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity;
        Optional<OrderEntity> optionalOrderEntity = orderService.findById(orderDTO.getId());

        if (optionalOrderEntity.isPresent()) orderEntity = optionalOrderEntity.get();
        else throw new RuntimeException("Incorrect ID of car");

        orderEntity.setId(orderDTO.getId());
        orderEntity.setPriceAdd(orderDTO.getPriceAdd());
        orderEntity.setFinPrice(orderDTO.getFinPrice());
        orderEntity.setFromWhatDate(orderDTO.getFromWhatDate());
        orderEntity.setIdCar(orderDTO.getIdCar());
        orderEntity.setIdUser(orderDTO.getIdUser());
        orderEntity.setIdOwner(orderDTO.getIdOwner());
        orderEntity.setAmountOfDays(orderDTO.getAmountOfDays());

        return orderEntity;
    }
}
