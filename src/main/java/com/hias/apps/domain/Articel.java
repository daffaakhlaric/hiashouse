package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "articel")
public class Articel {
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

    @Column(name = "description_second", nullable = true)
    @JsonProperty("description1")
    private String description1;

    @Column(name = "image_url", nullable = true)
    @JsonProperty("imageBannerUrl")
    private String imageBannerUrl;

    @ManyToOne
    @JoinColumn(name = "articel_category_id")
    private ArticelCategory articelCategory;

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

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getImageBannerUrl() {
        return imageBannerUrl;
    }

    public void setImageBannerUrl(String imageBannerUrl) {
        this.imageBannerUrl = imageBannerUrl;
    }

    public ArticelCategory getArticelCategory() {
        return articelCategory;
    }

    public void setArticelCategory(ArticelCategory articelCategory) {
        this.articelCategory = articelCategory;
    }
}
