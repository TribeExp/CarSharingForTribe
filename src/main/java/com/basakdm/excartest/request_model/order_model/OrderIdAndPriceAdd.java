package com.basakdm.excartest.request_model.order_model;

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
