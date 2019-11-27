package com.hias.apps.repository;

import com.hias.apps.domain.Inspiration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface InspirationRepository extends JpaRepository<Inspiration, Long> {

    List<Inspiration> findAll();

    @Modifying
    @Transactional
    @Query(value = "insert into inspiration (title,description,image_banner) values (:title,:description,:image)",
            nativeQuery = true)

    public void insertBanner(String title, String description,String image);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Inspiration WHERE id = :id", nativeQuery = true)
    public void deleteInspiration(@Param("id") Long id);



    @Modifying
    @Transactional
    @Query("UPDATE Inspiration SET title = :title , description = :description , image_banner = :image WHERE id = :id")
    public void updateBanner(String title, String description ,String image,Long id);
}
