package com.hias.apps.service;

import com.hias.apps.repository.SocialMediaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hias.apps.domain.SocialMediaUser;

@Service
public class SocialMediaUserService {
	
	@Autowired
	private SocialMediaUserRepository socialMediaUserRepository;
	
	public void insertSocialMediaUser(SocialMediaUser socialMediaUser) {
		socialMediaUserRepository.save(socialMediaUser);
	}
}
