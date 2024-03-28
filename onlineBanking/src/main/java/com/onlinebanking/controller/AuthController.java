package com.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.dto.JWTAuthResponse;
import com.onlinebanking.dto.LoginDTO;
import com.onlinebanking.entity.User;
import com.onlinebanking.service.AuthService;
import com.onlinebanking.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authserv;
	@Autowired
	private UserService userserv;
    @PostMapping("/register")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user) {
       User us= userserv.createUser(user);
       return new ResponseEntity<User>(us, HttpStatus.CREATED);
	}
    @PostMapping("/signin")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO logindto) {
		
		String token= authserv.login(logindto);
		  JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
	        jwtAuthResponse.setAccessToken(token);
		return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.ACCEPTED);
		
	}
}
