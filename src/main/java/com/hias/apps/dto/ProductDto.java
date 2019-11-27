package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("productCode")
    private String productCode;

    @JsonProperty("description")
    private String description;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("hashTag")
    private String hashTag;

    @JsonProperty("courier1")
    private String courier1;

    @JsonProperty("courier2")
    private String courier2;

    @JsonProperty("courier3")
    private String courier3;

    @JsonProperty("courier4")
    private String courier4;

    @JsonProperty("color")
    private String color;

    @JsonProperty("dimen")
    private String dimen;

    @JsonProperty("weight")
    private String weight;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("price")
    private Long price;


    @JsonProperty("picture1")
    private String picture1;


    @JsonProperty("picture2")
    private String picture2;


    @JsonProperty("picture3")
    private String picture3;


    @JsonProperty("picture4")
    private String picture4;


    @JsonProperty("picture5")
    private String picture5;


    @JsonProperty("picture6")
    private String picture6;

    @JsonProperty("categoryId")
    private Long categoryId;


    @JsonCreator
    public ProductDto(
            @JsonProperty("categoryId")
                    Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getHashTag(String hashTag) {
        return this.hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getCourier1(String courier1) {
        return this.courier1;
    }

    public void setCourier1(String courier1) {
        this.courier1 = courier1;
    }

    public String getCourier2(String courier2) {
        return this.courier2;
    }

    public void setCourier2(String courier2) {
        this.courier2 = courier2;
    }

    public String getCourier3(String courier3) {
        return this.courier3;
    }

    public void setCourier3(String courier3) {
        this.courier3 = courier3;
    }

    public String getCourier4(String courier4) {
        return this.courier4;
    }

    public void setCourier4(String courier4) {
        this.courier4 = courier4;
    }

    public String getProductName(String productName) {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode(String productCode) {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription(String description) {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOverview(String overview) {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }



    public String getColor(String color) {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getThumbnail(String thumbnail) {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getQuantity(Long quantity) {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice(Long price) {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPicture1(String picture1) {
        return this.picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2(String picture2) {
        return this.picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3(String picture3) {
        return this.picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getPicture4(String picture4) {
        return this.picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public String getPicture5(String picture5) {
        return this.picture5;
    }

    public void setPicture5(String picture5) {
        this.picture5 = picture5;
    }

    public String getPicture6(String picture6) {
        return this.picture6;
    }

    public void setPicture6(String picture6) {
        this.picture6 = picture6;
    }

    public Long getCategoryId(Long categoryId) {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDimen(String dimen) {
        return this.dimen;
    }

    public void setDimen(String dimen) {
        this.dimen = dimen;
    }

    public String getWeight(String weight) {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
