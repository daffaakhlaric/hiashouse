package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hias.apps.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name = "inspiration")
public class Inspiration {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "title")
//    @JsonProperty("title")
    private String title;

    @Column(name = "description")
//    @JsonProperty("description")
    private String description;

    @Column(name = "image_banner")
//    @JsonProperty("imageBanner")
    private String imagebanner;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagebanner() {
        return imagebanner;
    }

    public void setImagebanner(String imagebanner) {
        this.imagebanner = imagebanner;
    }
}
