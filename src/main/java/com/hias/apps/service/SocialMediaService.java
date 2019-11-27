package com.hias.apps.service;

import com.hias.apps.repository.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hias.apps.api.spotify.ProfileSpotifyController;
import com.hias.apps.api.spotify.dto.ProfileSpotifyDto;
import com.hias.apps.api.spotify.response.ProfileSpotifyResponse;
import com.hias.apps.domain.SocialMedia;
import com.hias.apps.dto.SocialMediaDto;

@Service
public class SocialMediaService {

	@Autowired
	private SocialMediaRepository socialMediaRepository;
	
	@Autowired
	private ProfileSpotifyController profileSpootifyController;
	
	public SocialMedia findSocialMediaByName(String name) {
		return socialMediaRepository.findSocialMediaByName(name);
	}
	
	public ProfileSpotifyResponse getProfileSpotify(SocialMediaDto socialMediaEl) {
		ProfileSpotifyDto psDto = new ProfileSpotifyDto();
		psDto.setAccountName(socialMediaEl.getAccountName());
		psDto.setToken(socialMediaEl.getCode());
		ResponseEntity<ProfileSpotifyResponse> response = profileSpootifyController.getProfiles(psDto);
		ProfileSpotifyResponse responseProfileSpotify = response.getBody();
		return responseProfileSpotify;
	}
}
