package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartDto {



    @JsonProperty("userId")
    private Long userId;

    @JsonCreator
    public CartDto(

            @JsonProperty("userId") Long userId) {
        this.userId = userId;

    }


    public Long getUserId(Long userId) {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



}
