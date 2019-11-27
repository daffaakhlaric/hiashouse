package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItemDto {


    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("qty")
    private Long qty;


    @JsonCreator
    public OrderItemDto(
            @JsonProperty("orderId")
                    Long orderId,
            @JsonProperty("productId")
                    Long productId) {
        this.orderId = orderId;
        this.productId = productId;

    }

    public Long getOrderId(Long[] orderId) {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId(Long[] productId) {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQty(Long[] qty) {
        return this.qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}
