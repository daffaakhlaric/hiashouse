package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "banner_image_web")
public class BannerImageWeb {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "banner_id")
    private BannerWeb bannerWeb;

    @Column(name = "image_url", nullable = true)
    @JsonProperty("imageBannerUrl")
    private String imageBannerUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BannerWeb getBannerWeb() {
        return bannerWeb;
    }

    public void setBannerWeb(BannerWeb bannerWeb) {
        this.bannerWeb = bannerWeb;
    }

    public String getImageBannerUrl() {
        return imageBannerUrl;
    }

    public void setImageBannerUrl(String imageBannerUrl) {
        this.imageBannerUrl = imageBannerUrl;
    }
}
