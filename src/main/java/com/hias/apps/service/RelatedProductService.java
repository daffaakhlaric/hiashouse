package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.*;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.CartRepository;
import java.util.List;

import com.hias.apps.repository.ProductRelatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RelatedProductService {

    @Autowired
    private ProductRelatedRepository productRelatedRepository;


    public List<RelatedProduct> getByProductId(Long id){

        return productRelatedRepository.findByProductId(id);
    }

}
