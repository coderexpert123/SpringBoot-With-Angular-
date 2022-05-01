package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.services.impl.userDetailServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private userDetailServiceImpl userDetailService;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	final String requestTokenHeade=request.getHeader("Authorization ");
		
		System.out.println(requestTokenHeade);
		String username=null;
		String jwtToken=null;
		
		if (requestTokenHeade!=null && requestTokenHeade.startsWith("Bearer ")) {
			
		try {
			
			
		
	jwtToken=requestTokenHeade.substring(7);
	username=this.jwtUtils.extractUsername(jwtToken);
		}
		
		
		catch (ExpiredJwtException e) {
			// TODO: handle exception
			e.printStackTrace();
			
			System.out.println("Token has been expired");
		}catch (Exception e) {
			// TODO: handle exception
		
			e.printStackTrace();
			System.out.println("Error");
		}
		}
		else {
			
			System.out.println("Invalid token");
		}
		
		
		if(username==null && SecurityContextHolder.getContext().
				getAuthentication()==null){
			
		final UserDetails userDetails=this.userDetailService.
				loadUserByUsername(username);
		
		
		if(this.jwtUtils.validateToken(jwtToken, userDetails)) {
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			usernamePasswordAuthentication.setDetails
			(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
		
		}else {
			
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
		}
	}

	
	
}
