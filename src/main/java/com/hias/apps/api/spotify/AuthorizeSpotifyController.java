package com.hias.apps.api.spotify;

import java.net.URLEncoder;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/spotify/auth")
@CrossOrigin
public class AuthorizeSpotifyController {

	@Value("${spotify.url.authorize}")
	private String urlAuth;
	
	@Value("${spotify.clientId}")
	private String clientId;
	
	@SuppressWarnings({ "unused", "unchecked" })
	@GetMapping
	public ResponseEntity<JSONObject> authorizeController(){
		StringBuilder builder = new StringBuilder();
		builder.append("?response_type=code");
		builder.append("&client_id=");
		builder.append(clientId);
		builder.append("&redirect_uri=https%3A%2F%2Fexample.com%2Fcallback");
		builder.append("&scope=user-read-private%20user-read-email&state=34fFs29kd09");
        String url =  urlAuth + builder.toString(); //URI REDIRECT STILL HARDCODE
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("url", url);
		jsonResponse.put("method", "GET");
		return new ResponseEntity<JSONObject>(jsonResponse,HttpStatus.OK);
	}
	
}
