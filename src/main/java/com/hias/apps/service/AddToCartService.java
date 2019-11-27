package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.*;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.AddToCartRepository;
import com.hias.apps.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AddToCartService {
    @Autowired
    private AddToCartRepository addToCartRepository;



    public List<AddToCart> getByUserId(Long id){

        return addToCartRepository.findByUserId(id);
    }

    public List<AddToCart> getAllcart(){

        return addToCartRepository.findAll();
    }

    public Optional<AddToCart> getByid(Long id){

        return addToCartRepository.findById(id);
    }


    public void insertAddTocart(Long userId,Long productId, Long amount){
        addToCartRepository.insertCart(userId,productId,amount);

    }

    public void deleteCartItem(Long id){
        addToCartRepository.deleteCartItem(id);

    }

    public void updateQty(Long id,Long amount){
        addToCartRepository.updateQty(id,amount);

    }

}
