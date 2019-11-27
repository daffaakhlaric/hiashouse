package com.hias.apps.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.hias.apps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hias.apps.domain.User;

@Service
public class ForgotService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SendEmailService sendEmailService;

	public Map<String, String> forgotPassword(String email) {
		Optional<User> userExist = userRepository.findByEmail(email);
		Map<String, String> mapMessage = new HashMap<>();
		if (userExist.isPresent()) {
			String uuid = UUID.randomUUID().toString();
			userExist.get().setResetPassword(uuid);
			userExist.get().setReset(true);
			userRepository.save(userExist.get());
			try {
				Map<String, String> mapParam = new HashMap<String, String>();
				mapParam.put("username", userExist.get().getUsername().toUpperCase());
				mapParam.put("linkValidate", "api-core-music.limu.tv/forgot?id=" +uuid);
				mapParam.put("uuid", "Click Here");
				sendEmailService.sendEmail("noreply@limu.tv", "Register Confirmation", userExist.get().getEmail(), "text",
						"FORGOT", mapParam);
				mapMessage.put("200","forgot password has been sent");
			} catch (Exception e) {
				mapMessage.put("500",e.getMessage().toString());
			}
		}else {
			mapMessage.put("200","user doesn't exist");
		}
		return mapMessage;
	}
	
	public User getUserByResetId(String id) {
		Optional<User> optionalUser = userRepository.findByResetid(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			return null;
		}
	}
}
