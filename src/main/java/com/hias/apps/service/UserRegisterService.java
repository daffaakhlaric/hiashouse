package com.hias.apps.service;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.hias.apps.repository.UserRegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hias.apps.domain.User;
import com.hias.apps.domain.UserProfile;
import com.hias.apps.domain.UserRegister;
import com.hias.apps.dto.RegisterDto;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.sun.xml.messaging.saaj.util.Base64;

@Service
public class UserRegisterService {

	@Autowired
	private UserRegistrationRepository userRegisterRepo;
	
	/**Save*/
	public void insertUserRegistration(UserRegister userRegister) {
		userRegisterRepo.save(userRegister);
	}
	
	/**Delete*/
	public void deleteUserRegistration(UserRegister userRegister) {
		userRegisterRepo.delete(userRegister);
	}
	
	/**List*/
	public List<UserRegister> getListUserRegister(){
		return userRegisterRepo.findAll();
	}
	
	/**Selected Element*/
	public UserRegister getSelectedUserRegister(Long id) {
		Optional<UserRegister> result = userRegisterRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	
	/**Send Email
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws IOException */
	public void sendEmail(String to, String subject, String content, String token) throws AddressException, MessagingException, IOException {
		
		OkHttpClient client = new OkHttpClient();
		com.squareup.okhttp.MediaType mediaType = com.squareup.okhttp.MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, 
				"{personalizations\":[{\"to\":[{\"email\":\"ceniebettygreditasari@gmail.com\",\"name\":\"John Doe\"}],\"subject\":\"Hello, World!\"}],\"content\": [{\"type\": \"text/plain\", \"value\": \"Heya!\"}],\"from\":{\"email\":\"siapakahgw@gmail.com\",\"name\":\"Sam Smith\"},\"reply_to\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"}}");
		com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
				  .url("https://api.sendgrid.com/v3/mail/send")
				  .post(body)
				  .addHeader("content-type", "application/json")
				  .addHeader("authorization", "token sendgrid")
				  .build();

		Response response = client.newCall(request).execute();
		System.out.println("Response >> " + response.isSuccessful());
	}
	
	/**Convert DTO to Object UserRegister */
	public UserRegister convertObject(RegisterDto dtoValue) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dtoValue, UserRegister.class);
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
	
	/**Generate Username To String Using Base64 */
	public String convertUsername(String username) {
		byte[] bytesEncoded = Base64.encode(username.getBytes());
		String userName = new String(bytesEncoded);
		return userName;
	}
	
	/**Create path Token For Email Registrtation */
	public String pathToken(String username, String id, String isWeb) {
		return "?id="+username+"&registration="+id+"&web="+ isWeb;
	}
	
	/**Calculate Expired Date For Token */
	public Date createExpired() {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
	
	/**Generate Base64 to String*/
	public String decodeBase64(String base64) {
		String decoded = Base64.base64Decode(base64);
		return decoded;
	}
	
	/**Get User Register By username, token*/
	public UserRegister getUserByUsernameToken(String username, String token) {
		Optional<UserRegister>  userRegister = userRegisterRepo.findUserByUsernameToken(username, token);
		if(userRegister.isPresent()) {
			return userRegister.get();
		}else {
			return null;
		}
	}

	/**Validate Process*/
	public boolean validateRegister(UserRegister userRegister) {
		Date now = new Date();
		if(userRegister != null) {
			if(userRegister.getExpiredToken().after(now) && userRegister.isConfirm() == false) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**Update Status IsConfirm When User Register Success validate */
	public void updateIsConfirm(String username) {
		try {
			userRegisterRepo.updateIsConfirmByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Convert User Register To User */
	public User convertRegisterToUser(UserRegister userRegister) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userRegister, User.class);
		
	}
	
	/**Count USer Exist By Username Or Email */
	public int checkUserExist(String username, String email) {
		return userRegisterRepo.countUserExist(username, email);
	}
	
	/**Find User Register By Email */
	public UserRegister findUserRegisterByEmail(String email) {
		Optional<UserRegister>  userRegister = userRegisterRepo.findUserRegisterByEmail(email);
		if(userRegister.isPresent()) {
			return userRegister.get();
		}else {
			return null;
		}
	}
	
	/**Updated UUID*/
	public void updatedUUID(String email, String uuid) {
		try {
			userRegisterRepo.updateUUID(email, uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Convert DTO to Object UserProfile */
	public UserProfile convertObjectUserProofile(User dtoValue) {
		UserProfile userProfile = new UserProfile();
//		userProfile.setCategoryUser(dtoValue.getCategoryUser());
		userProfile.setEmail(dtoValue.getEmail());
		userProfile.setId(dtoValue.getId());
		userProfile.setFullname(dtoValue.getFullname());
		userProfile.setPassword(dtoValue.getPassword());
		userProfile.setRegisterDate(dtoValue.getRegisterDate());
		userProfile.setStatus(dtoValue.getStatus());
		userProfile.setUsername(dtoValue.getUsername());
		userProfile.setImageProfile(dtoValue.getImageProfile());
		return userProfile;
	}
}
