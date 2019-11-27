package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorDto {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("productRelatedId")
    private Long productRelatedId;


    @JsonCreator
    public ColorDto(
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("productRelatedId")
                    Long productRelatedId) {

        this.productId = productId;
        this.productRelatedId = productRelatedId;

    }


    public Long getProductId(Long productId) {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductRelatedId(Long productRelatedId) {
        return this.productRelatedId;
    }

    public void setProductRelatedId(Long productRelatedId) {
        this.productRelatedId = productRelatedId;
    }
}
