package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hias.apps.domain.audit.DateAudit;

@Entity
@Table(name = "add_to_cart")
public class AddToCart implements Serializable{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product cartProduct;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//    @OneToOne(mappedBy = "add_to_cart")
//    private Order order;


    @Column(name = "amount")
    private Long amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(Product cartProduct) {
        this.cartProduct = cartProduct;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }



}

