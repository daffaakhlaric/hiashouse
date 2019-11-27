package com.hias.apps.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.hias.apps.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("Select pw from Discount pw where pw.code = :code")
    Optional<Discount> findByCode(@Param("code") String code);

}
