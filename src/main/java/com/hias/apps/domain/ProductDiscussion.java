package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hias.apps.domain.audit.DateAudit;

@Entity
@Table(name = "product_disscusion")
public class ProductDiscussion extends DateAudit implements Serializable{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @JsonProperty("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @JsonProperty("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @JsonProperty("discussion")
    @Column(name = "discussion", nullable = true)
    private String discussion;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }
}
