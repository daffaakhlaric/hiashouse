package com.hias.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hias.apps.domain.SocialMedia;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long>{

	@Query(
		     value = "SELECT * FROM social_media  where social_media_name = :socialMediaName", 
		     nativeQuery=true
	)
	public SocialMedia findSocialMediaByName(@Param("socialMediaName") String socialMediaName);
}
