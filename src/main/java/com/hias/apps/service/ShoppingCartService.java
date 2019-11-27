package com.hias.apps.service;

import com.hias.apps.exception.NotEnoughProductsInStockException;
import com.hias.apps.domain.Product;

import java.math.BigDecimal;
import java.util.Map;
public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}

