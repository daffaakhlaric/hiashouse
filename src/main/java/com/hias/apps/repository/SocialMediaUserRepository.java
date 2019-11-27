package com.hias.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hias.apps.domain.SocialMediaUser;

@Repository
public interface SocialMediaUserRepository extends JpaRepository<SocialMediaUser, Long>{

}
