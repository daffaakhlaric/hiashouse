package com.hias.apps.repository;

import com.hias.apps.domain.Order;
import com.hias.apps.dto.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Configuration
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    @Modifying
    @Transactional
    @Query(value = "insert into orders (user_id, user_address_id, total, sub_total, status, product_total, session, payment_type, product_item) values (:userId,:userAddressId,:total,:subTotal,:status,:productTotal,:session,:paymentType,:productItem)",
            nativeQuery = true)

     void insertOrder(@Param("userId") Long userId,@Param("userAddressId") Long userAddressId,@Param("total") Long total,@Param("subTotal")  Long subTotal,@Param("status") Long status,@Param("productTotal") Long productTotal,@Param("session") String session,@Param("paymentType") String paymentType,

                      @Param("productItem") List<ProductItem> productItem);

//    @Modifying
//    @Transactional
//    @Query(value = "insert into orders(user_id,user_address_id,tota,sub_total,status.product_total,session,payment_type,product_item) values (?,?,?,?,?,?,?,?,?)",
//    nativeQuery = true)
//    public void insertOrder(Long userId, Long userAddressId, Long total, Long subTotal, Long status, Long productTotal, String session, String paymentType, List<ProductItem> productItem);

    @Query("Select pw from Order pw where pw.id = :id")
    Optional<Order> findById(@Param("id") Long id);

//    @Query("Select pw from Order pw where product_id = :id")
//    List<Order> findByProductId(Long id);
    //
    @Query("Select pw from Order pw where user_id = :id")
    List<Order> findByUserId(Long id);

    @Query("Select pw from Order pw where user_address_id = :id")
    List<Order> findByUserAdressId(Long id);

    @Query("Select pw from Order pw where cart_id = :id")
    List<Order> findByCartId(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Order SET status = :status  WHERE id = :id")
    public void updateStatus(Long status, Long id);

//    Order save(Order newVehicle, Long userId);
}
