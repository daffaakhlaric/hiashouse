package com.hias.apps.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.hias.apps.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;


@Repository
public interface ProductWishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("Select pw from Wishlist pw where pw.id = :id")
    Optional<Wishlist> findById(@Param("id") Long id);

    @Query("Select pw from Wishlist pw where product_id = :id")
    List<Wishlist> findByProductId(Long id);
//
    @Query("Select pw from Wishlist pw where user_id = :id")
    List<Wishlist> findByUserId(Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into product_wishlist (user_id, product_id) values (:userId, :productId)",
            nativeQuery = true)

    public void insertWishlist(Long productId, Long userId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_wishlist WHERE id = :id", nativeQuery = true) // if want to write nativequery then mask nativeQuery  as true
    public void deleteWishlist(@Param("id") Long id);

}
