package com.hias.apps.repository;

import com.hias.apps.domain.AddToCart;
import com.hias.apps.domain.ToneProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToneProductRepository extends JpaRepository<ToneProduct, Long> {


    @Query("Select pw from ToneProduct pw where product_id = :id")
    List<ToneProduct> findByProductId(Long id);

}
