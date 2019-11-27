package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hias.apps.domain.User;

import java.io.Serializable;
import java.util.List;


public class OrderDto implements Serializable {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("userAddressId")
    private Long userAddressId;


    @JsonCreator
    public OrderDto(
            @JsonProperty("userId")
                    Long userId,
            @JsonProperty("userAddressId")
                    Long userAddressId) {
        this.userId = userId;
        this.userAddressId = userAddressId;

    }

    @JsonProperty("total")
    private Long total;

    @JsonProperty("subTotal")
    private Long subTotal;

    @JsonProperty("paymentType")
    private String paymentType;

    @JsonProperty("status")
    private Long status;

    @JsonProperty("productTotal")
    private Long productTotal;

    @JsonProperty("session")
    private String session;

    @JsonProperty("productItem")
    private List<ProductItem> productItem;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(Long productTotal) {
        this.productTotal = productTotal;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<ProductItem> getProductItem() {
        return productItem;
    }

    public void setProductItem(List<ProductItem> productItem) {
        this.productItem = productItem;
    }


}
