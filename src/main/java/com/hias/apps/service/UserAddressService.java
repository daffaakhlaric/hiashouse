package com.hias.apps.service;

import com.amazonaws.services.apigateway.model.Op;
import com.hias.apps.domain.*;
import com.hias.apps.dto.ProductDiscussionDto;
import com.hias.apps.repository.UserAddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressRepository userAddressRepository;



    public List<UserAddress> getByUserId(Long id){

        return userAddressRepository.findByUserId(id);
    }


    public void insertAddress(Long userId, String firstName, String lastName, String company, String country, String city, String address, String email, String phone, String postCode){
        userAddressRepository.insertAddress(userId,firstName,lastName,company,country,city,address,email,phone,postCode);

    }

}
