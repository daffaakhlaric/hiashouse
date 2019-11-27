package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "variant_product")
public class VariantProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "product_id_variant")
    private Product productVariant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(Product productVariant) {
        this.productVariant = productVariant;
    }
}
