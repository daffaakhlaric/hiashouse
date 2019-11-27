package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDiscussionDto {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("discussion")
    private String Discussion;

    @JsonCreator
    public ProductDiscussionDto(
            @JsonProperty("productId")
                    Long productId,
            @JsonProperty("userId") Long userId,
            @JsonProperty("discussion") String Discussion) {
        this.userId = userId;
        this.productId = productId;
        this.Discussion = Discussion;
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

    public String getDiscussion() {
        return Discussion;
    }

    public String getDiscussion(String discussion) {
        return Discussion;
    }

    public void setDiscussion(String discussion) {
        Discussion = discussion;
    }
}
