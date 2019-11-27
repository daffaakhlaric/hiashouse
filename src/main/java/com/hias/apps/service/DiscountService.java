package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.Discount;
import com.hias.apps.domain.CartItem;
import com.hias.apps.domain.ProductDiscussion;
import com.hias.apps.domain.Wishlist;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.DiscountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;


    public Optional<Discount> getByUserId(String code){

        return discountRepository.findByCode(code);
    }

}
