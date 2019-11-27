package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.*;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.CartRepository;
import java.util.List;

import com.hias.apps.repository.InspirationRelatedRepository;
import com.hias.apps.repository.InspirationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class InspirationService {
    @Autowired
    private InspirationRepository inspirationRepository;

    @Autowired
    private InspirationRelatedRepository inspirationRelatedRepository;



    public List<InspirationProduct> getByInspirationId(Long id){

        return inspirationRelatedRepository.findByInspirationId(id);
    }

    public List<Inspiration> getAllInspiration(){

        return inspirationRepository.findAll();
    }


    public void insertInspiration(String description, String title,String image){
        inspirationRepository.insertBanner(title,description,image);

    }

    public void insertInspirationRelated(Long inspirationId, Long productId){
        inspirationRelatedRepository.insertDiscussion(inspirationId,productId);

    }

    public void deleteInspiration(Long id){
        inspirationRepository.deleteInspiration(id);

    }

    public void updateInspiration(String title,String description,String image, Long id){
        inspirationRepository.updateBanner(title,description,image,id);

    }
}
