package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InspirationRelatedDto {

    @JsonProperty("inspirationId")
    private Long inspirationId;

    @JsonProperty("productId")
    private Long productId;

    @JsonCreator
    public InspirationRelatedDto(

            @JsonProperty("inspirationId") Long inspirationId,
            @JsonProperty("productId") Long productId) {
        this.inspirationId = inspirationId;
        this.productId = productId;

    }

    public Long getInspirationId(Long inspirationId) {
        return this.inspirationId;
    }

    public void setInspirationId(Long inspirationId) {
        this.inspirationId = inspirationId;
    }

    public Long getProductId(Long productId) {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
