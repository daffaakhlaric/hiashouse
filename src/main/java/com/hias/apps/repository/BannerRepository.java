package com.hias.apps.repository;

import com.hias.apps.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    List<Banner> findAll();

    @Modifying
    @Transactional
    @Query(value = "insert into banner (image_url,link) values (:imageUrl,:link)",
            nativeQuery = true)

    public void insertBanner(String imageUrl, String link);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM banner WHERE id = :id", nativeQuery = true)
    public void deleteBanner(@Param("id") Long id);



    @Modifying
    @Transactional
    @Query("UPDATE Banner SET image_url = :imageUrl , link = :link  WHERE id = :id")
    public void updateBanner(String imageUrl, String link,Long id);
}
