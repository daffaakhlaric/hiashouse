package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.Banner;
import com.hias.apps.domain.BannerWeb;
import com.hias.apps.domain.NewsLatter;
import com.hias.apps.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class NewsLatterService {
    @Autowired
    private NewsLatterRepository newsLatterRepository;

    @Autowired
    private BannerWebRepository BannerWebRepository;

    public Page<NewsLatter> getAllNewsLatter(Pageable pageable){

        return newsLatterRepository.findAll(pageable);
    }


    public List<BannerWeb> getAllBannerWeb(){

        return BannerWebRepository.findAll();
    }

    public void insertNewsLatter(String email){
        newsLatterRepository.insertNewsLatter(email);

    }

//    public void deleteBanner(Long id){
//        bannerRepository.deleteBanner(id);
//
//    }
//
//    public void updateBanner(String imageUrl,String link, Long id){
//        bannerRepository.updateBanner(imageUrl,link,id);
//
//    }
}
