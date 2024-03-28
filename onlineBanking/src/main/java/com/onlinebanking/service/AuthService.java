package com.onlinebanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.onlinebanking.config.JwtTokenProvider;
import com.onlinebanking.dto.LoginDTO;
@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	public String login(LoginDTO logindto) {
		Authentication authentication= authmanager.authenticate(new  UsernamePasswordAuthenticationToken(
				logindto.getUsername(), logindto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		 String token = jwtTokenProvider.generateToken(authentication);
		return token;
	}
}
