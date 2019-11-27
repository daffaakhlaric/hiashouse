package com.hias.apps.service;

import java.util.List;
import java.util.Optional;

import com.hias.apps.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hias.apps.domain.Role;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	/**save*/
	public void insertRole(Role role) {
		roleRepository.save(role);
	}
	
	/**Delete*/
	public void deleteRole(Role role) {
		roleRepository.delete(role);
	}
	
	/**List*/
	public List<Role> getListRole(){
		return roleRepository.findAll();
	}
	
	/**Selected Element By Id*/
	public Role getSelectedRole(Long id) {
		Optional<Role> result = roleRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	
	/** Selected Id Role By Name */
	public Role getSelectedRoleByName(String name) {
		return roleRepository.findIdByRoleName(name);
	}
}
