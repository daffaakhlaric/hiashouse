package com.hias.apps.repository;

import com.hias.apps.domain.AddToCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.hias.apps.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, Long> {
    //
    @Query("Select pw from AddToCart pw where pw.id = :id")
    Optional<AddToCart> findById(@Param("id") Long id);

    @Query("Select pw from AddToCart pw where product_id = :id")
    List<AddToCart> findByProductId(Long id);
    //
    @Query("Select pw from AddToCart pw where user_id = :id")
    List<AddToCart> findByUserId(Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into add_to_cart (user_id,product_id,amount) values (:userId,:productId,:amount)",
            nativeQuery = true)

    public void insertCart(Long userId, Long productId, Long amount);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM add_to_cart WHERE product_id = :id", nativeQuery = true)
    public void deleteCartItem(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE AddToCart SET amount = :amount  WHERE product_id = :id")
    public void updateQty(Long amount, Long id);
}
