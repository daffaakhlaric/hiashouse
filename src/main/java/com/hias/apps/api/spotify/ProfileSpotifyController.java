package com.hias.apps.api.spotify;

import com.hias.apps.api.spotify.dto.ProfileSpotifyDto;
import com.hias.apps.api.spotify.response.ProfileSpotifyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class ProfileSpotifyController {

	@Value("${spotify.url.profile}")
	private String urlProfile;
	
	public ResponseEntity<ProfileSpotifyResponse> getProfiles(@RequestBody ProfileSpotifyDto dto) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ dto.getToken());

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		 ResponseEntity<ProfileSpotifyResponse> response = restTemplate.exchange(
				urlProfile+ dto.getAccountName(), HttpMethod.GET, entity, ProfileSpotifyResponse.class);
		return response;
	}
}
