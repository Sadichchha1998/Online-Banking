package com.onlinebanking.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinebanking.entity.RolesAndAuthority;
import com.onlinebanking.entity.User;
import com.onlinebanking.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RolesAndAuthorityService rolesAndAuthorityService;

	@Override
	public User createUser(User user) {
		Optional<User> u = userRepo.findByUsername(user.getUsername());
		if (u.isPresent()) {
			throw new RuntimeException("User with " + user.getUsername() + " already present");
		}
		String hashpass = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashpass);
		Set<RolesAndAuthority> authoritiesSet = user.getAuthoritiesSet();

		Set<RolesAndAuthority> authoritiesSetManaged = new HashSet<>();
		for (RolesAndAuthority roles : authoritiesSet) {
			authoritiesSetManaged.add(rolesAndAuthorityService.getRolesAndAuthority(roles.getName()));
		}

		user.setAuthoritiesSet(authoritiesSetManaged);
		user.setAccounts(new ArrayList<>());
		return userRepo.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		Optional<User> u = userRepo.findByUsername(username);
		if (!u.isPresent()) {
			throw new RuntimeException("user not found with " + username);
		}

		return u.get();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = userRepo.findAll();
		if (list.isEmpty()) {
			throw new RuntimeException("User list is empty");
		}
		return list;
	}

	@Override
	public User deleteUser(String username) {
		Optional<User> u = userRepo.findByUsername(username);
		if (!u.isPresent()) {
			throw new RuntimeException("user not found with " + username);
		}
		userRepo.delete(u.get());
		return u.get();
	}

}
