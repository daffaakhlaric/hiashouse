package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BannerDescDto {


    @JsonProperty("imageUrl")
    private String imageUrl;


    @JsonProperty("link")
    private String link;


    public String getImageUrl(String imageUrl) {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink(String link) {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
