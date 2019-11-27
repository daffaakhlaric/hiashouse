package com.hias.apps.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hias.apps.dto.ChangePasswordDto;
import com.hias.apps.service.ChangePasswordService;
import com.hias.apps.service.UsersService;
import com.hias.apps.util.ErrorResponse;
import com.hias.apps.util.MapMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/change")
@CrossOrigin
@Api(value = "/change", description = "Change Password", produces = "application/json")
public class ChangePasswordController {

	@Autowired
	private UsersService userService;
	
	@Autowired
	private ChangePasswordService changeService;
	
	private MapMessage mapMessage;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Change Password", response = ChangePasswordDto.class)
	@ApiResponses(value={
		    @ApiResponse(code=200,message="Change Password Success", response=ChangePasswordDto.class),
		    @ApiResponse(code=500,message="Change Password Error"),
		    @ApiResponse(code=404,message="Change Password Not Found")})
	@PostMapping
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto){
		JSONObject response = new JSONObject();
		if(dto.getPassword().equals(dto.getConfirmationPassword())) {
			String hasPassword = userService.hashingPassword(dto.getPassword());
			try {
				changeService.changePassword(hasPassword, dto.getUsername());
				Map<String, String> mapMessage = new HashMap<>();
				mapMessage.put("message", "success");
				response.put("data", mapMessage);
				response.put("success", true);
				response.put("error", null);
			}catch (Exception e) {
				response.put("data", null);
				response.put("success", false);
				response.put("error", new ErrorResponse("Your Password can't be update caused"+ e.getMessage().toString(), 500));
			}
		}else {
			response.put("data", null);
			response.put("success", false);
			response.put("error", new ErrorResponse("Your Password can't be update", 500));
		}
		return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
	}
}
