package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedDto {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("productRelatedId")
    private Long productRelatedId;


    @JsonCreator
    public RelatedDto(
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("productRelatedId")
                    Long productRelatedId) {

        this.productId = productId;
        this.productRelatedId = productRelatedId;

    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductRelatedId() {
        return productRelatedId;
    }

    public void setProductRelatedId(Long productRelatedId) {
        this.productRelatedId = productRelatedId;
    }

}
