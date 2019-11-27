package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.BannerImageWeb;
import com.hias.apps.repository.BannerImageWebRepository;
import com.hias.apps.repository.CartItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BannerImageWebService {
    @Autowired
    private BannerImageWebRepository bannerImageWebRepository;

    public List<BannerImageWeb> getByBannerId(Long id){

        return bannerImageWebRepository.findByBannerId(id);
    }





}
