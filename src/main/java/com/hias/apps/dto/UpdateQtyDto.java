package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateQtyDto {

    @JsonProperty("amount")
    private Long amount;

    public Long getAmount(Long amount) {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
