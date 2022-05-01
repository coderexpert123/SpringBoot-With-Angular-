package com.exam.services.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserServices;
@Service
public class UserServiceImpl implements UserServices{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception  {
		
		User local=	this.userRepository.findByUsername
				(user.getUsername());
		if (local!=null) {
			System.out.println("user allraedy exists");
		 
			throw new Exception();
			
			
		}else {
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
				
			}
			
			user.getUserRoles().addAll(userRoles);
			local=this.userRepository.save(user);
			
			
		}
		return local;
	}




	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
		
	}




	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
	}

}
