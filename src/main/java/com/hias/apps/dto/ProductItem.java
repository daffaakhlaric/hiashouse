package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component("ProductItem")
public class ProductItem implements Serializable {

    public ProductItem(){}

    public ProductItem(String productName, String thumbnailUrl, Long price, Long qty) {
        super();
        this.productName = productName;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.qty = qty ;
    }

    @JsonProperty("productName")
    private String productName;
    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;
    @JsonProperty("price")
    private Long price;
    @JsonProperty("qty")
    private Long qty;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {

        return " { \"productName\":" + productName + ", \"price\":" + price + ", \"thumbnailUrl\":" + thumbnailUrl +  ", \"qty\":" + qty + "}";
    }
}
