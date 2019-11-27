package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefundDto {


    @JsonProperty("phone")
    private String phone;

    @JsonProperty("noOrder")
    private String noOrder;

    @JsonProperty("place")
    private String place;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    public String getPhone(String phone) {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNoOrder(String noOrder) {
        return this.noOrder;
    }

    public void setNoOrder(String noOrder) {
        this.noOrder = noOrder;
    }

    public String getPlace(String place) {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getReason(String reason) {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName(String fullName) {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
