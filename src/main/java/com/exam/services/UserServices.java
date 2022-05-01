package com.exam.services;

import java.util.Set;

import com.exam.models.User;
import com.exam.models.UserRole;

public interface UserServices {

	
	
	//User creation
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	
	public User getUser(String username);
	
	public void deleteUser(Long id);
	
	
	
}
