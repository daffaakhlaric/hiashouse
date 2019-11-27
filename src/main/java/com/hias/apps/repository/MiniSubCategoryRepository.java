package com.hias.apps.repository;

import com.hias.apps.domain.MiniSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

@Repository
public interface MiniSubCategoryRepository extends JpaRepository<MiniSubCategory,Long>{

    List<MiniSubCategory> findAll();

    Optional<MiniSubCategory> findById(Long id);

    @Query("Select c from MiniSubCategory c where sub_category_id = :id")
    List<MiniSubCategory> findBySubCategoryId(Long id);
}
