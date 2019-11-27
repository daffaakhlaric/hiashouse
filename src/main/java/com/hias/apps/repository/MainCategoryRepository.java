package com.hias.apps.repository;

import com.hias.apps.domain.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

@Repository
public interface MainCategoryRepository  extends JpaRepository<MainCategory, Long>{

    List<MainCategory> findAll();

    Optional<MainCategory> findById(Long id);

//    List<MainCategory> findAllByOrderByNameAsc();
}
