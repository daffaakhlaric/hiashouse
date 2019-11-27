package com.hias.apps.service;

import com.hias.apps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

	@Autowired
	private UserRepository userRepository;
	
	public void changePassword(String password, String username){
		userRepository.updatePassword(password, username);
	}
}
