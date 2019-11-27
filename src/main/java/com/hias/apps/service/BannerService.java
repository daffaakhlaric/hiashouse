package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.Banner;
import com.hias.apps.domain.BannerWeb;
import com.hias.apps.repository.BannerImageWebRepository;
import com.hias.apps.repository.BannerRepository;
import com.hias.apps.repository.BannerWebRepository;
import com.hias.apps.repository.CartItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerWebRepository BannerWebRepository;

    public List<Banner> getAllBanner(){

        return bannerRepository.findAll();
    }


    public List<BannerWeb> getAllBannerWeb(){

        return BannerWebRepository.findAll();
    }

    public void insertBanner(String imageUrl, String link){
        bannerRepository.insertBanner(imageUrl,link);

    }

    public void deleteBanner(Long id){
        bannerRepository.deleteBanner(id);

    }

    public void updateBanner(String imageUrl,String link, Long id){
        bannerRepository.updateBanner(imageUrl,link,id);

    }
}
