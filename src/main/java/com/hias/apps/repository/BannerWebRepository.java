package com.hias.apps.repository;

import com.hias.apps.domain.BannerWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

@Repository
public interface BannerWebRepository extends JpaRepository<BannerWeb, Long> {

    List<BannerWeb> findAll();

//    Optional<Banner> findById(Long bannerId);
}
