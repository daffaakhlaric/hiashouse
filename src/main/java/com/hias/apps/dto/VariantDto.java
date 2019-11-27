package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VariantDto {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("productVariantId")
    private Long productVariantId;


    @JsonCreator
    public VariantDto(
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("productVariantId")
                    Long productVariantId) {

        this.productId = productId;
        this.productVariantId = productVariantId;

    }


    public Long getProductId(Long productId) {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public Long getProductVariantId(Long productVariantId) {
        return this.productVariantId;
    }

    public void setProductVariantId(Long productVariantId) {
        this.productVariantId = productVariantId;
    }
}
