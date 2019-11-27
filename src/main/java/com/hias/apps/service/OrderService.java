package com.hias.apps.service;

import com.hias.apps.domain.*;
import com.hias.apps.dto.OrderDto;

import java.util.*;

import com.hias.apps.repository.OrderItemRepository;
import com.hias.apps.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public List<Order> getByUserId(Long id){

        return orderRepository.findByUserId(id);
    }

    public List<Order> getByCartId(Long id){

        return orderRepository.findByCartId(id);
    }

    public List<Order> getAllOrder(){

        return orderRepository.findAll();
    }

    public Optional<Order> getByid(Long id){

        return orderRepository.findById(id);
    }

    public void changeStatus(Long status, Long id){
        orderRepository.updateStatus(status,id);
    }

//    public void insertAddTocart(Long userId, Long userAddressId, Long total, Long subTotal, Long status, Long productTotal, String session, String paymentType, List<ProductItem> productItem){
//        orderRepository.insertOrder(userId,userAddressId,total,subTotal,status,productTotal,session,paymentType, productItem);
//
//    }

    public Long createVehicle(OrderDto orderDto) {
        Order newVehicle = new Order();
//        orderDto newVehicles = new orderDto()


//        newVehicle.setId(orderDto.get);
        newVehicle.setUserId(orderDto.getUserId());
        newVehicle.setUserAddressId(orderDto.getUserAddressId());
        newVehicle.setStatus(orderDto.getStatus());
        newVehicle.setPaymentType(orderDto.getPaymentType());
        newVehicle.setProductTotal(orderDto.getProductTotal());
        newVehicle.setTotal(orderDto.getTotal());
        newVehicle.setSubTotal(orderDto.getSubTotal());
        newVehicle.setSession(orderDto.getSession());
        newVehicle.setProductItem(orderDto.getProductItem());
//        newVehicle.setStatus(String.valueOf(Status.FOR_SALE));

        return orderRepository.save(newVehicle).getId();
    }

    public void insertOrderItem(Long orderId,Long productId,Long qty){
        orderItemRepository.insertOrderItem(orderId,productId,qty);

    }

    public List<OrderItem> getOrderItem(Long id){

        return orderItemRepository.findByOrderId(id);
    }
}
