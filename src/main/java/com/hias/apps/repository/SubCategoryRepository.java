package com.hias.apps.repository;

import com.hias.apps.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory , Long>{

    List<SubCategory> findAll();

    Optional<SubCategory> findById(Long id);

    @Query("Select c from SubCategory c where main_category_id = :id")
    List<SubCategory> findByMainCategoryId(Long id);
}
