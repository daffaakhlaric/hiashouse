package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inspiration_related_product")
public class InspirationProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inspiration_id")
    private Inspiration Inspiration;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.hias.apps.domain.Inspiration getInspiration() {
        return Inspiration;
    }

    public void setInspiration(com.hias.apps.domain.Inspiration inspiration) {
        Inspiration = inspiration;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
