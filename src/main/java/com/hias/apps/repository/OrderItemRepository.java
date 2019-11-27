package com.hias.apps.repository;

import com.hias.apps.domain.AddToCart;
import com.hias.apps.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into order_item (order_id,product_id,qty) values (:orderId,:productId,:qty)",
            nativeQuery = true)

    public void insertOrderItem(Long orderId,Long productId,Long qty);

    @Query("Select pw from Order pw where pw.id = :id")
    Optional<OrderItem> findById(@Param("id") Long id);

    //    @Query("Select pw from Order pw where product_id = :id")
//    List<Order> findByProductId(Long id);
    //
    @Query("Select pw from OrderItem pw where order_id = :id")
    List<OrderItem> findByOrderId(Long id);

    @Query("Select pw from Order pw where cart_id = :id")
    List<OrderItem> findByCartId(Long id);
}
