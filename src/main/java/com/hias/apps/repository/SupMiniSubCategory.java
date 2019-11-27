package com.hias.apps.repository;

import com.hias.apps.domain.SubCategory;
import com.hias.apps.domain.SupMiniCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupMiniSubCategory extends JpaRepository<SupMiniCategory, Long> {

    List<SupMiniCategory> findAll();

    Optional<SupMiniCategory> findById(Long id);


    @Query("Select c from SupMiniCategory c where sup_mini_category_id = :id")
    List<SupMiniCategory> findBySupMiniSubCategoryId(Long id);
}
