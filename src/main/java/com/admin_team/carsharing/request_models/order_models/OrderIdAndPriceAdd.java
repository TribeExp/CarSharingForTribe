package com.admin_team.carsharing.request_models.order_models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class OrderIdAndPriceAdd {
    private Long orderId;
    private Long priceAdd;
}
