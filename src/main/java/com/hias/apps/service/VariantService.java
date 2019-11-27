package com.hias.apps.service;

import com.hias.apps.domain.RelatedProduct;
import com.hias.apps.domain.VariantProduct;
import com.hias.apps.repository.ProductRelatedRepository;
import com.hias.apps.repository.VarianProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantService {

    @Autowired
    private VarianProductRepository varianProductRepository;


    public List<VariantProduct> getByProductId(Long id){

        return varianProductRepository.findByProductId(id);
    }
}
