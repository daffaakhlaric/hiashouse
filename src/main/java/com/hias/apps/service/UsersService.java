package com.hias.apps.service;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

import com.hias.apps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hias.apps.domain.User;
import com.hias.apps.domain.UserProfile;

@Service
public class UsersService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	/**save*/
	public void insertUser(User user) {
		userRepository.save(user);
	}
	
	/**Delete*/
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	/**List*/
	public List<User> getListUser(){
		return userRepository.findAll();
	}
	
	/**Selected Element*/
	public User getSelectedUser(Long id) {
		Optional<User> result = userRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	public List<User> getListSearchUser(String fullname){
		List<User> result = userRepository.findByFullName(fullname);

		return result;
	}
	
	/** Update User */
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	/**Find User By Username */
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	/**Login */
	public int login(String username, String password) {
		String hashPassword = hashingPassword(password);
		return userRepository.findUsernamePassword(username, hashPassword);
	}
	
	/**Convert Password to SHA256 */
	public String hashingPassword(String password) {
		try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(password.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 Optional<User> userOptional = findByUsername(username);
		 if(userOptional.isPresent()) {
			 return userOptional.get();
		 }else{
			 return null; 
		 }
		
	}
	
	/**Convert DTO to Object UserProfile */
	public UserProfile convertObjectUserProofile(User dtoValue) {
		UserProfile userProfile = new UserProfile();
		userProfile.setCategoryUser(dtoValue.getCategoryUser());
		userProfile.setEmail(dtoValue.getEmail());
		userProfile.setFullname(dtoValue.getFullname());
		userProfile.setPassword(dtoValue.getPassword());
		userProfile.setRegisterDate(dtoValue.getRegisterDate());
		userProfile.setStatus(dtoValue.getStatus());
		userProfile.setUsername(dtoValue.getUsername());
		userProfile.setImageProfile(dtoValue.getImageProfile());
		return userProfile;
	}
}
