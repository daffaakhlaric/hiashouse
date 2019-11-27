package com.hias.apps.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hias.apps.domain.Role;
import com.hias.apps.domain.User;
import com.hias.apps.domain.UserProfile;
import com.hias.apps.dto.LoginDto;
import com.hias.apps.security.JwtTokenProvider;
import com.hias.apps.service.UserRegisterService;
import com.hias.apps.service.UsersService;
import com.hias.apps.util.ErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/authenticate")
@Api(value = "/authenticate", description = "Authentication", produces = "application/json")
@CrossOrigin
public class LoginController {

	@Autowired
	private UsersService userService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRegisterService registerService;

	@ApiOperation(value = "Login Authentication", response = User.class)
	@ApiResponses(value={
		    @ApiResponse(code=200,message="Login Success", response=LoginDto.class),
		    @ApiResponse(code=500,message="Internal Server Error"),
		    @ApiResponse(code=404,message="Login Not Found")})
	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	public ResponseEntity<?> loginAction(@RequestBody LoginDto request) {
		JSONObject response = new JSONObject();
		String username = request.getUsername();
		Collection<Role> colRole = this.userService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles();
		List<String> listRole = new ArrayList<String>();
		if (colRole != null) {
			for (Role element : colRole) {
				listRole.add(element.getRoleName());
			}
			int userExist = userService.login(request.getUsername(), request.getPassword());
			if (userExist > 0) {
				String token = jwtTokenProvider.createToken(username, listRole);
				Optional<User> user = userService.findByUsername(username);
				UserProfile userProfile = null;
				if(user.isPresent())
					 userProfile = registerService.convertObjectUserProofile(user.get());
				
				Map<Object, Object> model = new HashMap<Object, Object>();
				model.put("token", token);
				model.put("type", "Bearer");
				model.put("user", userProfile);
				
				Map<String, Map<Object, Object>> mapResult = new HashMap<>();
				mapResult.put("login", model);
				
				response.put("data", mapResult);
				response.put("success", true);
				response.put("error", null);
				
			} else {
				response.put("data", null);
				response.put("success", false);
				response.put("error", new ErrorResponse("user and password does not match", 500));
			}
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		} else {
			response.put("data", null);
			response.put("success", false);
			response.put("error", new ErrorResponse("User does not role", 500));
			
			return new ResponseEntity<JSONObject>(response, HttpStatus.UNAUTHORIZED);
		}
	}
}
