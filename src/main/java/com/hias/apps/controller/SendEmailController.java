package com.hias.apps.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hias.apps.domain.UserRegister;
import com.hias.apps.dto.ResendEmailRegisterDto;
import com.hias.apps.service.SendEmailService;
import com.hias.apps.service.UserRegisterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/resendEmail")
@Api(value = "/resendEmail", description = "Resend Email Register", produces = "application/json")
public class SendEmailController {
	@Autowired
	private UserRegisterService registerService;

	@Autowired
	private SendEmailService sendEmailService;
	
	@ApiOperation(value = "Resend Email Register")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Email Has Been Sent"),
			@ApiResponse(code = 500, message = "Email Has Been Failed "),
			@ApiResponse(code = 404, message = "Email Not Found") })
	@PostMapping
	@SuppressWarnings("unchecked")
	public ResponseEntity<?> resendEmail(@RequestBody ResendEmailRegisterDto dto) {
		JSONObject response = new JSONObject();
		String uuid = UUID.randomUUID().toString();
		UserRegister userRegister = registerService.findUserRegisterByEmail(dto.getEmail());
		if (userRegister != null) {
			String usernameBase64 = registerService.convertUsername(userRegister.getUsername());
			String path = registerService.pathToken(usernameBase64, uuid, dto.getIsWeb());
			Map<String, String> mapParam = new HashMap<String, String>();
			mapParam.put("username", userRegister.getUsername().toUpperCase());
			mapParam.put("linkValidate", "api-core-music.limu.tv/validate" + path);
			mapParam.put("uuid", uuid);
			mapParam.put("usernameID", usernameBase64);
			try {
				registerService.updatedUUID(userRegister.getEmail(), uuid);
				sendEmailService.sendEmail("noreply@limu.tv", "Register Confirmation", userRegister.getEmail(), "text",
						"REGISTRATION", mapParam);
				response.put("message", "registration success");
			} catch (IOException e) {
				response.put("message", "Email cant be send caused " + e.getMessage().toString());
			}
		} else {
			response.put("message", "Email cant be found");
		}
		return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
	}
}
