package com.hias.apps.api.spotify;

import java.util.Map;

import com.hias.apps.api.spotify.dto.TokenSpotifyDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenSpotifyController {
	
	@Value("${spotify.url.token}")
	private String urlToken;
	
	@Value("${spotify.clientId}")
	private String key;
	
	@Value("${spotify.secretKey}")
	private String secret;
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<?> tokenController(@RequestBody TokenSpotifyDto dto){
		if(dto.getCode() != null) {
			try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "Basic " + key+secret);
				
				MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
				request.add("grant_type", "authorization_code");
				request.add("code", dto.getCode());
				request.add("redirect_uri", dto.getRedirectUri());
				
				HttpEntity<MultiValueMap<String, String>>  entity = new HttpEntity<MultiValueMap<String, String>>(request, headers);
				Map<String, String> response = restTemplate.postForObject(urlToken, entity , Map.class);
//				JSONObject responseJSON = new JSONObject();
//				responseJSON.put("token", response.get(key));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
