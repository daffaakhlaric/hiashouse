package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.CartItem;
import com.hias.apps.domain.ProductDiscussion;
import com.hias.apps.domain.Wishlist;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.CartItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;


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

    public List<CartItem> getByCartId(Long id){

        return cartItemRepository.findByCartId(id);
    }

//    public List<CartItem> getByUser(Long id){
//
//        return cartItemRepository.findByUserId(id);
//    }


    public void insertAddTocartItem(Long cartId, Long productId, Long amout){
        cartItemRepository.insertCartItem(cartId,productId,amout);

    }


    public void deleteCartItem(Long id){
        cartItemRepository.deleteCartItem(id);

    }

    public void updateQty(Long id,Long amount){
        cartItemRepository.updateQty(id,amount);

    }

    public void deleteCart(Long id){
        cartItemRepository.deleteCart(id);

    }
//
//    public void deleteProductWishlist(Long Id){
//        productWishlistRepository.deleteWishlist(Id);
//
//    }
}
