package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.UserRepository;
import com.exam.services.UserServices;
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
	@Autowired
	private UserServices userServices;

	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		user.setProfile("Default.png");
		
		Set<UserRole> roles=new HashSet<>();
		Role role=new Role();
		role.setRoleId(10L);
		role.setRoleName("NORMAL");
		
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);	
		return this.userServices.createUser(user, roles);
		
		
	}
	
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		return this.userServices.getUser(username);
		
		
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteuser(@PathVariable("id") Long id) {

	this.userServices.deleteUser(id);
	
		
	}
}
