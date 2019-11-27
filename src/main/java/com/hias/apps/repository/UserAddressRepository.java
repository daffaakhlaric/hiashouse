package com.hias.apps.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.hias.apps.domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

//    @Query("Select pw from Cart pw where pw.id = :id")
//    Optional<Cart> findById(@Param("id") Long id);

    @Query("Select pw from UserAddress pw where user_id = :id")
    List<UserAddress> findByUserId(@Param("id") Long id);

//    List<Cart> findAll();

    //    @Query("Select pw from Cart pw where user_id = :id")
    //    @Query("Select pw from Cart pw where product_id = :id")
//    List<Cart> findByProductId(Long id);
    //
    @Modifying
    @Transactional
    @Query(value = "insert into user_address (user_id,first_name,last_name,company_name,country,city,address,email,phone,post_code) values ( :userId,:firstName, :lastName, :company, :country , :city, :address, :email, :phone, :postCode)",
            nativeQuery = true)

    public void insertAddress(Long userId,String firstName, String lastName, String company, String country,String city, String address, String email, String phone, String postCode);
}
