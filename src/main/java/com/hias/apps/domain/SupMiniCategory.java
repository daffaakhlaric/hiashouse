package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "super_mini_sub_category")
public class SupMiniCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "category_name", nullable = true)
    @JsonProperty("categoryName")
    private String categoryname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sup_mini_category_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MiniSubCategory miniSubCategoryProperties;

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


    public MiniSubCategory getMiniSubCategoryProperties() {
        return miniSubCategoryProperties;
    }

    public void setMiniSubCategoryProperties(MiniSubCategory miniSubCategoryProperties) {
        this.miniSubCategoryProperties = miniSubCategoryProperties;
    }
}