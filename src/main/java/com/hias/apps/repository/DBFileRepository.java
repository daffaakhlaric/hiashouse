package com.hias.apps.repository;

import com.hias.apps.domain.DBFile;
import com.hias.apps.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
    @Query("Select c from DBFile c where product_id = :id")
      List<DBFile> findByProductId(Long id);
//
//    DBFile save(DBFile dbFile, Long id);
}