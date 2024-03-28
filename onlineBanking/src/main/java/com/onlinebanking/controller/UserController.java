package com.onlinebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.entity.User;
import com.onlinebanking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService us;
	@GetMapping("/{username}")
	public ResponseEntity<User> finduserbyusernameHandler(@PathVariable("username") String username){
		User user= us.getUserByUsername(username);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> findAllUserHandler(){
		List<User> list= us.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<User> deleteUserHandler(@PathVariable("username") String username){
		User user= us.deleteUser(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
