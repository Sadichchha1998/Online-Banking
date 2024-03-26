package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.entity.User;

public interface UserService {
	public User createUser(User user);

	public User getUserByUsername(String username);

	public List<User> getAllUsers();

	public User deleteUser(String username);
}
