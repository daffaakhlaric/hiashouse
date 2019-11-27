//package com.hias.apps.service.impl;
//
//import com.hias.apps.exception.NotEnoughProductsInStockException;
//import com.hias.apps.domain.Product;
//import com.hias.apps.repository.ProductRepository;
//import com.hias.apps.service.ShoppingCartService;
//import com.hias.apps.service.ProductService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.List;
//public class ProductServiceImpl extends ProductService {
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ProductServiceImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public List<Product> findAllProductsPageable( ) {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public Optional<Product> findById(Long id) {
//        return productRepository.findById(id);
//    }
//}
