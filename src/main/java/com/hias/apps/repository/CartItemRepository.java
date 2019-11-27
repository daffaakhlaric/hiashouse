package com.hias.apps.repository;

import com.hias.apps.domain.Cart;
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
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//
    @Query("Select pw from CartItem pw where pw.id = :id")
    Optional<CartItem> findById(@Param("id") Long id);

    @Query("Select pw from CartItem pw where product_id = :id")
    List<CartItem> findByProductId(Long id);
    //
    @Query("Select pw from CartItem pw where cart_id = :id")
    List<CartItem> findByCartId(Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into cart_item (cart_id,product_id,amount) values (:cartId,:productId,:amount)",
            nativeQuery = true)

    public void insertCartItem(Long cartId, Long productId, Long amount);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_item WHERE product_id = :id", nativeQuery = true)
    public void deleteCartItem(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem SET amount = :amount  WHERE product_id = :id")
    public void updateQty(Long amount, Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_item WHERE cart_id = :id", nativeQuery = true)
    public void deleteCart(@Param("id") Long id);
}
