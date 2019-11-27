package com.hias.apps.repository;

import com.hias.apps.domain.InspirationProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface InspirationRelatedRepository extends JpaRepository<InspirationProduct, Long> {

    List<InspirationProduct> findAll();


    @Query("Select pw from InspirationProduct pw where inspiration_id = :id")
    List<InspirationProduct> findByInspirationId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into inspiration_related_product (inspiration_id, product_id) values (:inspirationId, :productId)",
            nativeQuery = true)

    public void insertDiscussion(Long inspirationId, Long productId);
}
