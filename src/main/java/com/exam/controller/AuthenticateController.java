package com.exam.controller;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.models.jwtRequest;
import com.exam.models.jwtResponse;
import com.exam.services.impl.userDetailServiceImpl;

@RestController
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private userDetailServiceImpl userDetailService;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest 
			jwtRequest) throws Exception{
				
		
		try {
			
		authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
			
		} catch (UsernameNotFoundException e) {
 
		e.printStackTrace();
		throw new Exception("User not found ...");
		
		
		}
		
		UserDetails userDetails=this.userDetailService.loadUserByUsername(jwtRequest.getUsername());

		String token=this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new jwtResponse(token));
		
		
	}
	private void authenticate(String username, String password) throws Exception {	
		try {
			
			authenticationManager.authenticate
			(new UsernamePasswordAuthenticationToken(username, password));
			
			
		}catch (DisabledException e) {
			
			
			throw new Exception("User DIABLED"+e.getMessage());
			
		
		}catch (BadCredentialsException e) {
			
			throw new Exception("Invalid Username"+e.getMessage());
		}
	}
}
