package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.OrderDTO;
import com.basakdm.excartest.entity.OrderEntity;
import com.basakdm.excartest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class ConvertOrders {

    @Autowired
    private static OrderService orderService;

    public static OrderDTO mapOrder(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(orderEntity.getId());
        orderDTO.setPriceAdd(orderEntity.getPriceAdd());
        orderDTO.setFinPrice(orderEntity.getFinPrice());
        orderDTO.setFrom_what_date(orderEntity.getFrom_what_date());
        orderDTO.setId_car(orderEntity.getId_car());
        orderDTO.setId_user(orderEntity.getId_user());
        orderDTO.setId_owner(orderEntity.getId_owner());
        orderDTO.setAmount_of_days(orderEntity.getAmount_of_days());

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
        orderEntity.setFrom_what_date(orderDTO.getFrom_what_date());
        orderEntity.setId_car(orderDTO.getId_car());
        orderEntity.setId_user(orderDTO.getId_user());
        orderEntity.setId_owner(orderDTO.getId_owner());
        orderEntity.setAmount_of_days(orderDTO.getAmount_of_days());

        return orderEntity;
    }
}
