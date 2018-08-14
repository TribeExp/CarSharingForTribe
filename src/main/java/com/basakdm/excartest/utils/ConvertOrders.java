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
        orderDTO.setFinPrice(orderEntity.getFinPrice());
        orderDTO.setId(orderEntity.getId());
        orderDTO.setAmount_of_days(orderEntity.getAmount_of_days());
        orderDTO.setFrom_what_date(orderEntity.getFrom_what_date());
        orderDTO.setPriceAdd(orderEntity.getPriceAdd());
        return orderDTO;
    }

    public static OrderEntity mapOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity;
        Optional<OrderEntity> optionalOrderEntity = orderService.findById(orderDTO.getId());

        if (optionalOrderEntity.isPresent()) orderEntity = optionalOrderEntity.get();
        else throw new RuntimeException("Incorrect ID of car");

        orderEntity.setFinPrice(orderDTO.getFinPrice());
        orderEntity.setId(orderDTO.getId());
        orderEntity.setAmount_of_days(orderDTO.getAmount_of_days());
        orderEntity.setFrom_what_date(orderDTO.getFrom_what_date());
        orderEntity.setPriceAdd(orderDTO.getPriceAdd());
        return orderEntity;
    }
}
