package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.Banner;
import com.hias.apps.domain.BannerWeb;
import com.hias.apps.domain.NewsLatter;
import com.hias.apps.domain.Refund;
import com.hias.apps.repository.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RefundService {
    @Autowired
    private RefundRepository refundRepository;

    public List<Refund> getAllRefund(){

        return refundRepository.findAll();
    }


    public void insertRefund(String fullName,String phone,String place,String noOrder,String email, String reason){
        refundRepository.insertRefund(fullName,phone,place,noOrder,email,reason);

    }


}
