package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.*;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.AddToCartRepository;
import com.hias.apps.repository.ArticelCategoryRepository;
import com.hias.apps.repository.ArticelRepository;
import com.hias.apps.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ArticelService {
    @Autowired
    private ArticelRepository articelRepository;

    @Autowired
    private ArticelCategoryRepository articelCategoryRepository;



    public List<Articel> getByArticelCategoryId(Long id){

        return articelRepository.findByArticelCategoryId(id);
    }

    public List<ArticelCategory> getAllCategoryArticel(){

        return articelCategoryRepository.findAll();
    }


}
