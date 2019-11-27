package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsLatterDto {


    @JsonProperty("email")
    private String email;

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
