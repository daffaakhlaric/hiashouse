package com.hias.apps.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.hias.apps.domain.Role;
import com.hias.apps.domain.User;
import com.hias.apps.domain.UserRegister;
import com.hias.apps.dto.ValidateRegisterDto;
import com.hias.apps.service.RoleService;
import com.hias.apps.service.UserRegisterService;
import com.hias.apps.service.UsersService;

@RestController
@CrossOrigin
@RequestMapping("/validate")
public class ValidateRegisterController {
	@Autowired
	private UserRegisterService registerService;

	@Autowired
	private UsersService userService;

	@Autowired
	private RoleService roleService;

	/** Constants */
	public static final String USER_TYPE_FREE = "member";
	public static final String STATUS_USER_REGISTER = "active";

	@GetMapping
	public RedirectView validateAction(ValidateRegisterDto dto) {
		RedirectView redirectView = new RedirectView();
		Date now = new Date();
		List<Role> listRole = new ArrayList<Role>();
		String username = registerService.decodeBase64(dto.getId());
		UserRegister userRegister = registerService.getUserByUsernameToken(username, dto.getRegistration());
		boolean isConfirm = registerService.validateRegister(userRegister);
		if (isConfirm) {
			registerService.updateIsConfirm(username);
			Role roleId = roleService.getSelectedRoleByName(USER_TYPE_FREE);
			listRole.add(roleId);
			User user = registerService.convertRegisterToUser(userRegister);
			user.setRoles(listRole);
			user.setUserRegister(userRegister);
			user.setStatus(STATUS_USER_REGISTER);
			user.setCategoryUser(USER_TYPE_FREE);
			user.setRegisterDate(now);
			userService.insertUser(user);
			
		    redirectView.setUrl("http://reg.limu.tv/success.html");
		    
		} else {
			redirectView.setUrl("http://reg.limu.tv/failed.html");
		}
		return redirectView;
	}
}
