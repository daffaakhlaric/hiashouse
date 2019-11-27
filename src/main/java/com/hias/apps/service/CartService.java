package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.Cart;
import com.hias.apps.domain.CartItem;
import com.hias.apps.domain.ProductDiscussion;
import com.hias.apps.domain.Wishlist;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;


//    public Optional<Wishlist> getProductWishlistById(Long id){
//
//        return productWishlistRepository.findById(id);
//    }
//
//    public List<Wishlist> getProductWishlistByProductId(Long id){
//
//        return productWishlistRepository.findByProductId(id);
//    }
//
//
//    public List<CartItem> getByUserId(Long id){
//
//        return cartItemRepository.findByUserId(id);
//    }


    public List<Cart> getByUserId(Long id){

        return cartRepository.findByUserId(id);
    }

    public List<Cart> getAllcart(){

        return cartRepository.findAll();
    }

    public Optional<Cart> getByid(Long id){

        return cartRepository.findById(id);
    }


    public void insertAddTocart(Long userId){
        cartRepository.insertCart(userId);

    }
//
//    public void deleteProductWishlist(Long Id){
//        productWishlistRepository.deleteWishlist(Id);
//
//    }
}
