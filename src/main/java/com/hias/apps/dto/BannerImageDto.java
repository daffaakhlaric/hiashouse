package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BannerImageDto {



    @JsonProperty("banner")
    private Long bannerId;

    @JsonCreator
    public BannerImageDto(

            @JsonProperty("bannerId") Long bannerId) {
        this.bannerId = bannerId;

    }


    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }
}
