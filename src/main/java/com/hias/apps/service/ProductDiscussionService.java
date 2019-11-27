package com.hias.apps.service;

import com.hias.apps.domain.ProductDiscussion;

import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.DiscussionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductDiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    public void insertDiscussion(ProductDiscussionDto productDiscussionDto) {
//        discussionRepository.save(productDiscussionDto);
    }


//    public void  ProductDiscussionService(ProductDiscussion ) {
//        // Normalize file name
//
//
//            ProductDiscussion productDiscussion = new ProductDiscussion();
//
//            return discussionRepository.save();
//
//    }

    public void insertDisc(ProductDiscussion productDiscussion) {
        discussionRepository.save(productDiscussion);
    }

    public List<ProductDiscussion> getListDiscussionByProductId(Long id){
        List<ProductDiscussion> result = discussionRepository.findByProductId(id);

        return result;
    }

    public void insertProductDiscussion(Long userId, Long productId, String discussion){
    discussionRepository.insertDiscussion(productId, userId, discussion);

    }
//
//    public void insertDisc(int userId, int productId, String discussion) {
//    }

//    public void savedisc(Long productId, Long userId, String discussion) {
//        discussionRepository.save();
//    }

//    public void savedisc(Long productId, Long userId, String discussion) {
//        discussionRepository.save(productId,userId,discussion);
//    }
}
