package com.hias.apps.repository;

import com.hias.apps.domain.BannerImageWeb;
import com.hias.apps.domain.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BannerImageWebRepository extends JpaRepository<BannerImageWeb, Long> {

    @Query("Select c from BannerImageWeb c where banner_id = :id")
    List<BannerImageWeb> findByBannerId(Long id);
}
