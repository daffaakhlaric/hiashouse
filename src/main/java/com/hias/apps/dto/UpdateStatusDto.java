package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateStatusDto {

    @JsonProperty("status")
    private Long status;

    public Long getStatus(Long status) {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
