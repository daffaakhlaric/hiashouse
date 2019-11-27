package com.hias.apps.service;

import com.hias.apps.domain.ProductRating;

import com.hias.apps.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductRatingService {

    @Autowired
    private RatingRepository ratingRepository;


    public List<ProductRating> getListRatingByProductId(Long id){
        List<ProductRating> result = ratingRepository.findByProductId(id);

        return result;
    }

    public void insertProductRating(Long userId, Long productId, Long rating){
        ratingRepository.insertRating(productId, userId, rating);

    }

    public void deleteProductRating(Long Id){
        ratingRepository.deleteRating(Id);

    }
}
