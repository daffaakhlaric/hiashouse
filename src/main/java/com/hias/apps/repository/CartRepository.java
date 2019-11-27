package com.hias.apps.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.hias.apps.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("Select pw from Cart pw where pw.id = :id")
    Optional<Cart> findById(@Param("id") Long id);

    @Query("Select pw from Cart pw where user_id = :id")
    List<Cart> findByUserId(@Param("id") Long id);

    List<Cart> findAll();

//    @Query("Select pw from Cart pw where user_id = :id")
    //    @Query("Select pw from Cart pw where product_id = :id")
//    List<Cart> findByProductId(Long id);
    //
    @Modifying
    @Transactional
    @Query(value = "insert into product_cart (id,user_id) values (:userId,:userId)",
            nativeQuery = true)

    public void insertCart(Long userId);
}
