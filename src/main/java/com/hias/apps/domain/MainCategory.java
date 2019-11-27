package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

import com.hias.apps.domain.audit.DateAudit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Id;

@Entity
@Table(name = "main_category")
public class MainCategory{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "category_name", nullable = true)
    @JsonProperty("categoryName")
    private String categoryname;

    @Column(name = "description", nullable = true)
    @JsonProperty("description")
    private String description;

    @Column(name = "image_url", nullable = true)
    @JsonProperty("imageUrl")
    private String imageUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
