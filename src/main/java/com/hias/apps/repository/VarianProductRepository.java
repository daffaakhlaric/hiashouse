package com.hias.apps.repository;

import com.hias.apps.domain.AddToCart;
import com.hias.apps.domain.VariantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface VarianProductRepository extends JpaRepository<VariantProduct, Long> {


    @Query("Select pw from VariantProduct pw where product_id = :id")
    List<VariantProduct> findByProductId(Long id);


    @Modifying
    @Transactional
    @Query(value = "insert into variant_product (product_id, product_id_variant) values (:productId, :productVariantId)",
            nativeQuery = true)

    public void insertVariant(Long productId, Long productVariantId);
}
