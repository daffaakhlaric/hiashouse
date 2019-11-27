package com.hias.apps.repository;

import com.hias.apps.domain.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<ProductRating, Long> {
    @Query("Select pw from ProductRating pw where pw.id = :id")
    Optional<ProductRating> findById(@Param("id") Long id);

    @Query("Select pw from ProductRating pw where product_id = :id")
    List<ProductRating> findByProductId(Long id);
    //
    @Query("Select pw from ProductRating pw where user_id = :id")
    List<ProductRating> findByUserId(Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into product_rating (user_id, product_id, rating) values (:userId, :productId, :rating)",
            nativeQuery = true)

    public void insertRating(Long productId, Long userId,Long rating);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_rating WHERE id = :id", nativeQuery = true) // if want to write nativequery then mask nativeQuery  as true
    public void deleteRating(@Param("id") Long id);
}
