package com.hias.apps.repository;

import com.hias.apps.domain.Articel;
import com.hias.apps.domain.ArticelCategory;
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
public interface ArticelCategoryRepository extends JpaRepository<ArticelCategory, Long> {
    //


    List<ArticelCategory>  findAll();

}
