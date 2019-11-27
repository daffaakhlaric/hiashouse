package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "product_cart")
public class Cart implements Serializable{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JsonIgnore
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private User user;
//    @OneToOne(mappedBy = "product_cart")
//    private CartItem cartItem;

    @OneToMany(mappedBy="cart")
    private List<CartItem> cartItem;



    public Cart() {

    }
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

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }



    //
//    public CartItem getCartItem() {
//        return cartItem;
//    }
//
//    public void setCartItem(CartItem cartItem) {
//        this.cartItem = cartItem;
//    }

    //    public CartItem getCartItem() {
//        return cartItem;
//    }
//
//    public void setCartItem(CartItem cartItem) {
//        this.cartItem = cartItem;
//    }

//    public Discount getCartDiscount() {
//        return cartDiscount;
//    }
//
//    public void setCartDiscount(Discount cartDiscount) {
//        this.cartDiscount = cartDiscount;
//    }
//
//    public Float getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(Float totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public Float getTotalCargoPrice() {
//        return totalCargoPrice;
//    }
//
//    public void setTotalCargoPrice(Float totalCargoPrice) {
//        this.totalCargoPrice = totalCargoPrice;
//    }
}

