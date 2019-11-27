package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRatingDto {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("rating")
    private Long Rating;

    @JsonCreator
    public ProductRatingDto(
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("userId") Long userId,
            @JsonProperty("rating") Long Rating) {
        this.userId = userId;
        this.productId = productId;
        this.Rating = Rating;
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

    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRating(Long rating) {
        return Rating;
    }

    public void setRating(Long rating) {
        Rating = rating;
    }
}
