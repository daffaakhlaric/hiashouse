package com.hias.apps.repository;

import com.hias.apps.domain.Articel;
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
public interface ArticelRepository extends JpaRepository<Articel, Long> {
    //
    @Query("Select pw from Articel pw where pw.id = :id")
    Optional<Articel> findById(@Param("id") Long id);

    @Query("Select pw from Articel pw where articel_category_id = :id")
    List<Articel> findByArticelCategoryId(Long id);

}
