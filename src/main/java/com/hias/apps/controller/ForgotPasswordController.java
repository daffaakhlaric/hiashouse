package com.hias.apps.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hias.apps.domain.User;
import com.hias.apps.dto.ForgotPasswordDto;
import com.hias.apps.service.ForgotService;
import com.hias.apps.service.UsersService;
import com.hias.apps.util.ErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/forgot")
@CrossOrigin
@Api(value = "/forgot", description = "Forgot Password", produces = "application/json")
public class ForgotPasswordController {
	
	@Autowired
	private ForgotService forgotService;
	
	@Autowired
	private UsersService userService;
	
	Map<String, String> mapMessage = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Forgot Password", response = ForgotPasswordDto.class)
	@ApiResponses(value={
		    @ApiResponse(code=200,message="Forgot Password Success", response=ForgotPasswordDto.class),
		    @ApiResponse(code=500,message="Forgot Password Error"),
		    @ApiResponse(code=404,message="Forgot Password Not Found")})
	@PostMapping
	public ResponseEntity<?> forgotPass(@RequestBody ForgotPasswordDto dto) {
		Map<String, String> response = forgotService.forgotPassword(dto.getEmail());
		JSONObject resp = new JSONObject();
		if(response.containsKey("200")) {
			mapMessage.put("message", "Recovery link has been sent to " + dto.getEmail());
			resp.put("data", mapMessage);
			resp.put("success", true);
			resp.put("error", null);
		} else{
			resp.put("data", null);
			resp.put("success", false);
			resp.put("error",  new ErrorResponse("Forgot Password Failed", 500));
		}
		return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@GetMapping
	public ResponseEntity<?> validateForgotPass(String id){
		JSONObject resp = new JSONObject();
		User user =  forgotService.getUserByResetId(id);
		user.setReset(false);
		user.setResetPassword("");
		userService.updateUser(user);
		resp.put("username", user.getUsername());
		if(user != null) {
			mapMessage.put("message", "Recovery link has been validate with username " + user.getUsername());
			resp.put("data", mapMessage);
			resp.put("success", true);
			resp.put("error", null);
		}else {
			resp.put("data", null);
			resp.put("success", false);
			resp.put("error",  new ErrorResponse("Validate has been failed", 500));
		}
		return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	}
}
