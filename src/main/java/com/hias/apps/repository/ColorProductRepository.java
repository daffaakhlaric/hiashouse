package com.hias.apps.repository;

import com.hias.apps.domain.AddToCart;
import com.hias.apps.domain.ColorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorProductRepository extends JpaRepository<ColorProduct, Long> {


    @Query("Select pw from ColorProduct pw where product_id = :id")
    List<ColorProduct> findByProductId(Long id);


    @Modifying
    @Transactional
    @Query(value = "insert into color_product (product_id, product_id_related) values (:productId, :productRelatedId)",
            nativeQuery = true)

    public void insertRelated(Long productId, Long productRelatedId);
}
