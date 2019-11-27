package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WishlistDto {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("userId")
    private Long userId;

    @JsonCreator
    public WishlistDto(
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("userId") Long userId,
            @JsonProperty("discussion") String Discussion) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getProductId(Long productId) {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId(Long userId) {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
