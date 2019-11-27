package com.hias.apps.repository;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.hias.apps.domain.ProductDiscussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface DiscussionRepository extends JpaRepository<ProductDiscussion, Long> {


    @Query("Select pw from ProductDiscussion pw where pw.id = :id")
    Optional<ProductDiscussion> findById(@Param("id") Long id);

    @Query("Select pw from ProductDiscussion pw where product_id = :id")
    List<ProductDiscussion> findByProductId(Long id);
    //
    @Query("Select pw from ProductDiscussion pw where user_id = :id")
    List<ProductDiscussion> findByUserId(Long id);


    @Modifying
    @Transactional
    @Query(value = "insert into product_disscusion (user_id, product_id, discussion) values (:userId, :productId,  :discussion)",
            nativeQuery = true)

    public void insertDiscussion(Long productId, Long userId , String discussion);

//    void save();

//    void save(Long productId, Long userId, St ring discussion);
//
//    @Transactional
//    public void insertDiscussion(ProductDiscussion productDiscussion) {
//        entityManager.createNativeQuery("INSERT INTO ProductDiscussion (user_id, product_id, discussion) VALUES (?,?,?)")
//                .setParameter(1, productDiscussion.getProduct().getId())
//                .setParameter(2, productDiscussion.getUser().getId())
//                .setParameter(3, productDiscussion.getDiscussion())
//                .executeUpdate();
//    }
}
