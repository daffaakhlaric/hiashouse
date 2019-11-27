package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "banner_web")
public class BannerWeb {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "title", nullable = true)
    @JsonProperty("title")
    private String title;

    @Column(name = "subtitle", nullable = true)
    @JsonProperty("subTitle")
    private String subTitle;

    @Column(name = "description", nullable = true)
    @JsonProperty("description")
    private String description;

    @Column(name = "discount", nullable = true)
    @JsonProperty("discount")
    private String discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
