package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.ProductDiscussion;
import com.hias.apps.domain.Wishlist;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.ProductWishlistRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ProductWishlistService {
    @Autowired
    private ProductWishlistRepository productWishlistRepository;


    public Optional<Wishlist> getProductWishlistById(Long id){

        return productWishlistRepository.findById(id);
    }

    public List<Wishlist> getProductWishlistByProductId(Long id){

        return productWishlistRepository.findByProductId(id);
    }


    public List<Wishlist> getProductWishlistByUsertId(Long id){

        return productWishlistRepository.findByUserId(id);
    }

    public void insertProductWishlist(Long userId, Long productId){
        productWishlistRepository.insertWishlist(productId, userId);

    }

    public void deleteProductWishlist(Long Id){
        productWishlistRepository.deleteWishlist(Id);

    }
}
