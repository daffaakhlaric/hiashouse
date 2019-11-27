package com.hias.apps.repository;

import com.hias.apps.domain.NewsLatter;
import com.hias.apps.domain.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund,Long>{

    List<Refund> findAll();

    Optional<Refund> findById(Long id);

    //    @Query("Select c from NewsLatter c where sub_category_id = :id")
//    List<MiniSubCategory> findBySubCategoryId(Long id);
    @Modifying
    @Transactional
    @Query(value = "insert into refund (full_name,phone,place,no_order,email,reason) values (:fullName,:phone,:place,:noOrder,:email,:reason)",
            nativeQuery = true)

    public void insertRefund(String fullName,String phone,String place,String noOrder,String email, String reason);
}
