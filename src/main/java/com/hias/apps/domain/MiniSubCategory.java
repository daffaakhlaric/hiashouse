package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hias.apps.domain.audit.DateAudit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "mini_sub_category")
public class MiniSubCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "category_name", nullable = true)
    @JsonProperty("categoryName")
    private String categoryname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_category_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SubCategory subCategoryProperties;

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

    public SubCategory getSubCategoryProperties() {
        return subCategoryProperties;
    }

    public void setSubCategoryProperties(SubCategory subCategoryProperties) {
        this.subCategoryProperties = subCategoryProperties;
    }
}