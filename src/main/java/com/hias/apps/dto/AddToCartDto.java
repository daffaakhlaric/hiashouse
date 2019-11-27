package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddToCartDto {


    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("amount")
    private Long amount;

    @JsonCreator
    public AddToCartDto(
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("userId")
                    Long userId,
            @JsonProperty("amount") Long amount) {
        this.amount = amount;
        this.userId = userId;
        this.productId = productId;

    }

    public Long getUserId(Long userId) {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId(Long productId) {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAmount(Long amount) {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
