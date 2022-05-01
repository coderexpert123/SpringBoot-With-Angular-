package com.exam;

import java.util.HashSet;
import java.util.Set;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.services.UserServices;

@SpringBootApplication
public class ExamApplication implements CommandLineRunner {
	@Autowired
	public UserServices userServices;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("starting code");
//		
//		User user=new User();
//		
//		user.setFirstName("Nishant");
//		user.setLastName("Singh");
//		user.setUsername("Monu");
//		user.setPassword("axkbd");
//		user.setEmail("a@gmail.com");
//		user.setProfile("Default.png");
//		
//	
//		
//		Role role1=new Role();
//		role1.setRoleId(123L);
//		role1.setRoleName("ADMIN");
//		
//		
//		Set<UserRole> userRoleset=new HashSet<>();
//		UserRole userRole=new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		 
//		
//		userRoleset.add(userRole);
//		
//		
//		User user1=this.userServices.createUser(user, userRoleset);
//		System.out.println(user1.getUsername());

	}

	
}
