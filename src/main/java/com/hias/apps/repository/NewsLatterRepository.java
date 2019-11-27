package com.hias.apps.repository;

import com.hias.apps.domain.NewsLatter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface NewsLatterRepository extends JpaRepository<NewsLatter,Long>{

    Page<NewsLatter> findAll(Pageable pageable);

    Optional<NewsLatter> findById(Long id);

//    @Query("Select c from NewsLatter c where sub_category_id = :id")
//    List<MiniSubCategory> findBySubCategoryId(Long id);
    @Modifying
    @Transactional
    @Query(value = "insert into news_latter (email) values (:email)",
            nativeQuery = true)

    public void insertNewsLatter(String email);
}
