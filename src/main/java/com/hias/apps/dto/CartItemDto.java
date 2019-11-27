package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemDto {


    @JsonProperty("cartId")
    private Long cartId;

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("amount")
    private Long amount;

    @JsonCreator
    public CartItemDto(
            @JsonProperty("cartId")
                    Long cartId,
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("amount") Long amount) {

        this.cartId = cartId;
        this.productId = productId;
        this.amount = amount;

    }

    public Long getCartId(Long cartId) {
        return this.cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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
