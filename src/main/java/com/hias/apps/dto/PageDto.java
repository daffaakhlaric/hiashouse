package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageDto {
    @JsonProperty("page")
    private int page;

    @JsonProperty("size")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
