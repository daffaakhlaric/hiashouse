package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InspirationDto {


    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image")
    private String imageBanner;


    public String getTitle(String title) {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription(String description) {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageBanner(String imageBanner) {
        return this.imageBanner;
    }

    public void setImageBanner(String imageBanner) {
        this.imageBanner = imageBanner;
    }
}
